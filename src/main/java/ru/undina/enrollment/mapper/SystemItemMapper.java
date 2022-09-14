package ru.undina.enrollment.mapper;

import lombok.experimental.UtilityClass;
import ru.undina.enrollment.dto.SystemItem;
import ru.undina.enrollment.dto.SystemItemHistoryUnit;
import ru.undina.enrollment.dto.SystemItemImport;
import ru.undina.enrollment.model.SystemItemEntity;
import ru.undina.enrollment.model.SystemItemHistoryEntity;
import ru.undina.enrollment.model.SystemItemType;
import ru.undina.enrollment.repository.SystemItemHistoryRepository;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class SystemItemMapper {
    public static SystemItemEntity toSystemItem(SystemItemImport systemItemImport) {
        return SystemItemEntity.builder()
                .id(systemItemImport.getId())
                .parentId(systemItemImport.getParentId())
                .url(systemItemImport.getUrl())
                .type(systemItemImport.getType())
                .size(systemItemImport.getSize())
                .build();
    }

    public static SystemItemHistoryUnit toSystemItemHistoryUnit(SystemItemEntity systemItemEntity) {
        return SystemItemHistoryUnit.builder()
                .id(systemItemEntity.getId())
                .url(systemItemEntity.getUrl())
                .parentId(systemItemEntity.getParentId())
                .type(systemItemEntity.getType())
                .size(systemItemEntity.getSize())
                .date(systemItemEntity.getDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'")))
                .build();
    }

    public static SystemItemHistoryUnit toSystemItemHistoryUnitHistory(SystemItemHistoryEntity systemItemEntity) {
        return SystemItemHistoryUnit.builder()
                .id(systemItemEntity.getItemId())
                .url(systemItemEntity.getUrl())
                .parentId(systemItemEntity.getParentId())
                .type(systemItemEntity.getType())
                .size(systemItemEntity.getSize())
                .date(systemItemEntity.getDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'")))
                .build();
    }

    public static SystemItem toSystemItemDto(SystemItemEntity systemItemEntity) {
        List<SystemItem> children;
        if(systemItemEntity.getType().equals(SystemItemType.FILE)){
            children = null;
        }
        else {
            List<SystemItemEntity> children1 = systemItemEntity.getChildren();
             children = children1.stream()
                    .map(SystemItemMapper::toSystemItemDto)
                    .collect(Collectors.toList());

        }
        return SystemItem.builder()
                .id(systemItemEntity.getId())
                .url(systemItemEntity.getUrl())
                .parentId(systemItemEntity.getParentId())
                .type(systemItemEntity.getType())
                .size(systemItemEntity.getSize())
                .date(systemItemEntity.getDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'")))
                .children(children)
                .build();
    }

    public static SystemItemHistoryEntity toSystemItemHistoryEntity(SystemItemEntity systemItemEntity) {
        return SystemItemHistoryEntity.builder()
                .itemId(systemItemEntity.getId())
                .url(systemItemEntity.getUrl())
                .parentId(systemItemEntity.getParentId())
                .type(systemItemEntity.getType())
                .size(systemItemEntity.getSize())
                .date(systemItemEntity.getDate())
                .build();
    }
}
