package ru.undina.enrollment.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class SizeException extends RuntimeException {
    public SizeException(String message) {
        super(message);
    }
}
