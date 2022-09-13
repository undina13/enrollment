package ru.undina.enrollment.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.undina.enrollment.model.SystemItem;
import ru.undina.enrollment.model.SystemItemType;

import java.util.List;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SystemItemDto {

    private String id;

    private String date;

    private String parentId;

    private String url;

    private SystemItemType type;

    private Integer size;

    private List<SystemItemDto> children;
}
