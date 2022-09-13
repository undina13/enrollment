package ru.undina.enrollment.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.undina.enrollment.dto.SystemItemHistoryUnit;

import java.util.List;
@Data
@AllArgsConstructor
public class SystemItemHistoryResponse {
    private List<SystemItemHistoryUnit> items;
}
