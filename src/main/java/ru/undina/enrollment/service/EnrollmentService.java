package ru.undina.enrollment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.undina.enrollment.dto.SystemItemDto;
import ru.undina.enrollment.dto.SystemItemImport;
import ru.undina.enrollment.exception.*;
import ru.undina.enrollment.mapper.SystemItemMapper;
import ru.undina.enrollment.model.SystemItem;
import ru.undina.enrollment.model.SystemItemHistoryResponse;
import ru.undina.enrollment.model.SystemItemImportRequest;
import ru.undina.enrollment.model.SystemItemType;
import ru.undina.enrollment.repository.EnrollmentRepository;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.UUID;
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
        String updateDate = itemImportRequest.getUpdateDate();
        List<SystemItemImport> items = itemImportRequest.getItems();
//        Set<String> idSet = items.stream()
//                .map(SystemItemImport::getId)
//                .collect(Collectors.toSet());
//        if (idSet.size() != items.size()) {
//            throw new BadRequestException("Double id in the list");
//        }
        for (SystemItemImport itemImport : items) {
            SystemItem systemItem = SystemItemMapper.toSystemItem(itemImport);
            systemItem.setDate(updateDate);

//            if (systemItem.getParentId() != null) {
//                SystemItem parent = repository.findById(systemItem.getParentId())
//                        .orElseThrow(() -> new ItemNotFoundException("SystemItem not found"));
//
//                if (parent.getType().equals(SystemItemType.FILE)) {
//                    throw new FileParentException("File can`t be a parent");
//                }
//            }
//
//            if ((systemItem.getType().equals(SystemItemType.FILE) &&
//                    (systemItem.getSize() == null || systemItem.getSize() <= 0))
//                    || ((systemItem.getType().equals(SystemItemType.FOLDER)) &&
//                    (systemItem.getSize() == null || systemItem.getSize() >= 0))) {
//                throw new SizeException("Wrong size in item");
//            }
//
//            if ((systemItem.getUrl() == null && systemItem.getType().equals(SystemItemType.FILE))
//                    || (systemItem.getUrl() != null && systemItem.getType().equals(SystemItemType.FOLDER))) {
//                throw new UrlException("Wrong size in url");
//            }

            repository.save(systemItem);
        }
    }

    public void delete(String id, String date) {

        SystemItem item = repository.findById(id).orElseThrow(() -> new ItemNotFoundException("SystemItem not found"));
        if (item.getParentId() != null) {
            SystemItem parent = repository.findById(item.getParentId())
                    .orElseThrow(() -> new ItemNotFoundException("SystemItem not found"));
            parent.setDate(date);
            repository.save(parent);
        }
        repository.delete(item);
    }

    public SystemItemDto getById(String  id) {

        return SystemItemMapper.toSystemItemDto(repository.findById(id)
                .orElseThrow(() -> new ItemNotFoundException("SystemItem not found")));
    }


    public SystemItemHistoryResponse getByUpdate(Date date) {
        return null;
    }

    public SystemItemHistoryResponse getHistory(String id, String dateStart, String  dateEnd) {
        return null;
    }
}
