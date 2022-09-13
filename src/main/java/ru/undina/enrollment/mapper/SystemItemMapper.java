package ru.undina.enrollment.mapper;

import lombok.experimental.UtilityClass;
import ru.undina.enrollment.dto.SystemItemDto;
import ru.undina.enrollment.dto.SystemItemHistoryUnit;
import ru.undina.enrollment.dto.SystemItemImport;
import ru.undina.enrollment.model.SystemItem;
import ru.undina.enrollment.model.SystemItemType;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class SystemItemMapper {
    public static SystemItem toSystemItem(SystemItemImport systemItemImport) {
        return SystemItem.builder()
                .id(systemItemImport.getId())
                .parentId(systemItemImport.getParentId())
                .url(systemItemImport.getUrl())
                .type(systemItemImport.getType())
                .size(systemItemImport.getSize())
                .build();
    }

    public static SystemItemHistoryUnit toSystemItemHistoryUnit(SystemItem systemItem) {
        return SystemItemHistoryUnit.builder()
                .id(systemItem.getId())
                .url(systemItem.getUrl())
                .parentId(systemItem.getParentId())
                .type(systemItem.getType())
                .size(systemItem.getSize())
                .date(systemItem.getDate())
                .build();
    }

    public static SystemItemDto toSystemItemDto(SystemItem systemItem) {
        List<SystemItemDto> children;
        if(systemItem.getType().equals(SystemItemType.FILE)){
            children = null;
        }
        else {
            List<SystemItem> children1 = systemItem.getChildren();
             children = children1.stream()
                    .map(item -> SystemItemMapper.toSystemItemDto(item))
                    .collect(Collectors.toList());

        }
        return SystemItemDto.builder()
                .id(systemItem.getId())
                .url(systemItem.getUrl())
                .parentId(systemItem.getParentId())
                .type(systemItem.getType())
                .size(systemItem.getSize())
                .date(systemItem.getDate())
                .children(children)
                .build();
    }
}
