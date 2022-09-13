package ru.undina.enrollment.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import ru.undina.enrollment.model.SystemItemType;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SystemItemHistoryUnit {
    @NotNull
    private UUID id;

    private String url;

    private UUID parentId;

    @NotNull
    private SystemItemType type;

    private int size;

    @NotNull
    private String date;
}
