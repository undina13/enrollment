package ru.undina.enrollment.schemas;

import java.time.LocalDateTime;
import java.util.List;

public class SystemItemImportRequest {
   private List<SystemItemImport> items;
    private LocalDateTime updateDate;
}
