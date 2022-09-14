package ru.undina.enrollment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.undina.enrollment.dto.SystemItem;
import ru.undina.enrollment.dto.SystemItemHistoryUnit;
import ru.undina.enrollment.dto.SystemItemImport;
import ru.undina.enrollment.exception.BadRequestException;
import ru.undina.enrollment.exception.ItemNotFoundException;
import ru.undina.enrollment.mapper.SystemItemMapper;
import ru.undina.enrollment.model.SystemItemEntity;
import ru.undina.enrollment.model.SystemItemHistoryResponse;
import ru.undina.enrollment.model.SystemItemImportRequest;
import ru.undina.enrollment.model.SystemItemType;
import ru.undina.enrollment.repository.SystemItemHistoryRepository;
import ru.undina.enrollment.repository.SystemItemRepository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class EnrollmentService {
    private final SystemItemRepository itemRepository;
    private final SystemItemHistoryRepository historyRepository;

    @Autowired
    public EnrollmentService(SystemItemRepository itemRepository, SystemItemHistoryRepository historyRepository) {
        this.itemRepository = itemRepository;
        this.historyRepository = historyRepository;
    }

    @Transactional
    public void imports(SystemItemImportRequest itemImportRequest) {
        LocalDateTime updateDate = itemImportRequest.getUpdateDate();
        List<SystemItemImport> items = itemImportRequest.getItems();
        Set<String> idSet = items.stream()
                .map(SystemItemImport::getId)
                .collect(Collectors.toSet());
        if (idSet.size() != items.size()) {
            throw new BadRequestException("Validation Failed");
        }
        for (SystemItemImport itemImport : items) {
            SystemItemEntity systemItemEntity = SystemItemMapper.toSystemItem(itemImport);

            systemItemEntity.setDate(updateDate);
            if (systemItemEntity.getType().equals(SystemItemType.FILE) && !(systemItemEntity.getSize() > 0)) {
                throw new BadRequestException("Validation Failed");
            }
            if (systemItemEntity.getType().equals(SystemItemType.FOLDER) && systemItemEntity.getSize() > 0) {
                throw new BadRequestException("Validation Failed");
            }

            if ((systemItemEntity.getUrl() == null && systemItemEntity.getType().equals(SystemItemType.FILE))
                    || (systemItemEntity.getUrl() != null && systemItemEntity.getType()
                    .equals(SystemItemType.FOLDER))) {
                throw new BadRequestException("Validation Failed");
            }

            if (systemItemEntity.getParentId() != null) {
                SystemItemEntity parent = itemRepository.findById(systemItemEntity.getParentId())
                        .orElseThrow(() -> new ItemNotFoundException("Item not found"));

                if (parent.getType().equals(SystemItemType.FILE)) {
                    throw new BadRequestException("Validation Failed");
                }
                historyRepository.save(SystemItemMapper.toSystemItemHistoryEntity(parent));
                setParentSize(parent, systemItemEntity);
            }
            itemRepository.save(systemItemEntity);
            historyRepository.save(SystemItemMapper.toSystemItemHistoryEntity(systemItemEntity));
        }
    }

    @Transactional
    public void delete(String id, String date) {
        SystemItemEntity item = itemRepository.findById(id)
                .orElseThrow(() -> new ItemNotFoundException(("Item not found")));
        if (item.getParentId() != null) {
            SystemItemEntity parent = itemRepository.findById(item.getParentId())
                    .orElseThrow(() -> new ItemNotFoundException("Item not found"));
            parent.setDate(LocalDateTime.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'")));
            itemRepository.save(parent);
            historyRepository.save(SystemItemMapper.toSystemItemHistoryEntity(parent));
        }
        if (!item.getChildren().isEmpty()) {
            List<String> items = item.getChildren()
                    .stream()
                    .map(item1 -> item1.getId())
                    .collect(Collectors.toList());
            for (String item1 : items) {
                itemRepository.delete(itemRepository.findById(item1).get());
                historyRepository.deleteAllByItemId(item1);
            }
        }
        itemRepository.delete(item);
        historyRepository.deleteAllByItemId(item.getId());
    }

    public SystemItem getById(String id) {
        return SystemItemMapper.toSystemItemDto(itemRepository.findById(id)
                .orElseThrow(() -> new ItemNotFoundException(("Item not found"))));
    }

    private void setParentSize(SystemItemEntity parent, SystemItemEntity item) {
        if (item.getSize() != null) {
            if(parent.getSize()!=null){
            parent.setSize(parent.getSize() + item.getSize());}
            else {
                parent.setSize(item.getSize());
            }
        }
        parent.setDate(item.getDate());
        itemRepository.save(parent);
        historyRepository.save(SystemItemMapper.toSystemItemHistoryEntity(parent));
        if (parent.getParentId() != null) {
            SystemItemEntity itemParent = itemRepository.findById(parent.getParentId())
                    .orElseThrow(() -> new ItemNotFoundException(("Item not found")));
            setParentSize(itemParent, item);
        }
    }

    public SystemItemHistoryResponse getByUpdate(String date) {
        LocalDateTime end = LocalDateTime.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'"));
        LocalDateTime start = end.minusDays(1);
        List<SystemItemHistoryUnit> listItem = itemRepository.getAllByDateBetween(start, end)
                .stream()
                .map(SystemItemMapper::toSystemItemHistoryUnit)
                .collect(Collectors.toList());
        return new SystemItemHistoryResponse(listItem);
    }

    public SystemItemHistoryResponse getHistory(String id, String dateStart, String dateEnd) {
        LocalDateTime start = LocalDateTime.parse(dateStart, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'"));
        LocalDateTime end = LocalDateTime.parse(dateEnd, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'"));
        if (start.isAfter(end)) {
            throw new BadRequestException("Validation Failed");
        }

        List<SystemItemHistoryUnit> listItem = historyRepository.findAllByDateGreaterThanEqualAndDateBeforeAndItemId(start, end, id)
                .stream()
                .map(SystemItemMapper::toSystemItemHistoryUnitHistory)
                .collect(Collectors.toList());
        return new SystemItemHistoryResponse(listItem);
    }
}
