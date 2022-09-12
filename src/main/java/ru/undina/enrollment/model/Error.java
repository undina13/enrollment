package ru.undina.enrollment.model;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Error {
    private final Integer code;

    private final String message;
}
