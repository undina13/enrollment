package ru.undina.enrollment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.undina.enrollment.dto.SystemItemDto;
import ru.undina.enrollment.dto.SystemItemHistoryUnit;
import ru.undina.enrollment.dto.SystemItemImport;
import ru.undina.enrollment.exception.BadRequestException;
import ru.undina.enrollment.exception.ItemNotFoundException;
import ru.undina.enrollment.mapper.SystemItemMapper;
import ru.undina.enrollment.model.SystemItem;
import ru.undina.enrollment.model.SystemItemHistoryResponse;
import ru.undina.enrollment.model.SystemItemImportRequest;
import ru.undina.enrollment.model.SystemItemType;
import ru.undina.enrollment.repository.EnrollmentRepository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class EnrollmentService {
    private final EnrollmentRepository repository;

    @Autowired
    public EnrollmentService(EnrollmentRepository repository) {
        this.repository = repository;
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
            SystemItem systemItem = SystemItemMapper.toSystemItem(itemImport);

            systemItem.setDate(updateDate);
            if (systemItem.getType().equals(SystemItemType.FILE) && !(systemItem.getSize() > 0)) {
                throw new BadRequestException("Validation Failed");
            }
            if (systemItem.getType().equals(SystemItemType.FOLDER) && systemItem.getSize() > 0) {
                throw new BadRequestException("Validation Failed");
            }

            if ((systemItem.getUrl() == null && systemItem.getType().equals(SystemItemType.FILE))
                    || (systemItem.getUrl() != null && systemItem.getType().equals(SystemItemType.FOLDER))) {
                throw new BadRequestException("Validation Failed");
            }

            if (systemItem.getParentId() != null) {
                SystemItem parent = repository.findById(systemItem.getParentId())
                        .orElseThrow(() -> new ItemNotFoundException("Item not found"));

                if (parent.getType().equals(SystemItemType.FILE)) {
                    throw new BadRequestException("Validation Failed");
                }
                setParentSize(parent, systemItem);
            }
            repository.save(systemItem);
        }
    }

    @Transactional
    public void delete(String id, String date) {
        SystemItem item = repository.findById(id).orElseThrow(() -> new ItemNotFoundException(("Item not found")));
        if (item.getParentId() != null) {
            SystemItem parent = repository.findById(item.getParentId())
                    .orElseThrow(() -> new ItemNotFoundException("Item not found"));
            parent.setDate(LocalDateTime.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'")));
            repository.save(parent);
        }
        if (!item.getChildren().isEmpty()) {
            List<String> items = item.getChildren()
                    .stream()
                    .map(item1 -> item1.getId())
                    .collect(Collectors.toList());
            for (String item1 : items) {
                repository.delete(repository.findById(item1).get());
            }
        }
        repository.delete(item);
    }

    public SystemItemDto getById(String id) {
        return SystemItemMapper.toSystemItemDto(repository.findById(id)
                .orElseThrow(() -> new ItemNotFoundException(("Item not found"))));
    }

    private void setParentSize(SystemItem parent, SystemItem item) {

        parent.setSize(parent.getSize() + item.getSize());
        parent.setDate(item.getDate());
        repository.save(parent);
        if (parent.getParentId() != null) {
            SystemItem itemParent = repository.findById(parent.getParentId())
                    .orElseThrow(() -> new ItemNotFoundException(("Item not found")));
            setParentSize(itemParent, item);
        }
    }

    public SystemItemHistoryResponse getByUpdate(String date) {
        LocalDateTime end = LocalDateTime.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'"));
        LocalDateTime start = end.minusDays(1);
        List<SystemItemHistoryUnit> listItem = repository.getAllByDateBetween(start, end)
                .stream()
                .map(SystemItemMapper::toSystemItemHistoryUnit)
                .collect(Collectors.toList());
        return new SystemItemHistoryResponse(listItem);
    }

    public SystemItemHistoryResponse getHistory(String id, String dateStart, String dateEnd) {
        return null;
    }
}
