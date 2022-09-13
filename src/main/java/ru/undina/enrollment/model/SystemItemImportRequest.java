package ru.undina.enrollment.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import ru.undina.enrollment.dto.SystemItemImport;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
@Builder
public class SystemItemImportRequest {
    private List<SystemItemImport> items;


    private String updateDate;
}
