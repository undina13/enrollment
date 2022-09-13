package ru.undina.enrollment.model;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
public class Error {
    private final Integer code;

    private final String message;
}
