package ru.undina.enrollment.mapper;

import lombok.experimental.UtilityClass;
import org.springframework.format.annotation.DateTimeFormat;
import ru.undina.enrollment.dto.SystemItemDto;
import ru.undina.enrollment.dto.SystemItemHistoryUnit;
import ru.undina.enrollment.dto.SystemItemImport;
import ru.undina.enrollment.model.SystemItem;
import ru.undina.enrollment.model.SystemItemType;

import java.sql.Timestamp;
import java.text.Format;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Formatter;
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
                .date(systemItem.getDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'")))
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
                    .map(SystemItemMapper::toSystemItemDto)
                    .collect(Collectors.toList());

        }

        return SystemItemDto.builder()
                .id(systemItem.getId())
                .url(systemItem.getUrl())
                .parentId(systemItem.getParentId())
                .type(systemItem.getType())
                .size(systemItem.getSize())
                .date(systemItem.getDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'")))
                .children(children)
                .build();
    }
}
