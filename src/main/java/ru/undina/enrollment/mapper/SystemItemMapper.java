package ru.undina.enrollment.mapper;

import lombok.experimental.UtilityClass;
import ru.undina.enrollment.dto.SystemItemHistoryUnit;
import ru.undina.enrollment.dto.SystemItemImport;
import ru.undina.enrollment.model.SystemItem;

import java.util.ArrayList;

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
}
