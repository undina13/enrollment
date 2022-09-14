package ru.undina.enrollment.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.undina.enrollment.model.SystemItemType;

import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SystemItemHistoryUnit {
    @NotNull
    private String id;

    private String url;

    private String parentId;

    @NotNull
    private SystemItemType type;

    private int size;

    @NotNull
    private String date;
}
