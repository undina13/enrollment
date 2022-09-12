package ru.undina.enrollment.model;

import ru.undina.enrollment.dto.SystemItemImport;

import java.time.LocalDateTime;
import java.util.List;

public class SystemItemImportRequest {
    private List<SystemItemImport> items;
    private LocalDateTime updateDate;
}
