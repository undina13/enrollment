package ru.undina.enrollment.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;
import ru.undina.enrollment.dto.SystemItemImport;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class SystemItemImportRequest {

    private List<SystemItemImport> items;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    private LocalDateTime updateDate;
}
