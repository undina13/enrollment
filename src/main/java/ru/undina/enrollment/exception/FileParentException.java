package ru.undina.enrollment.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class FileParentException extends RuntimeException {
    public FileParentException(String message) {
        super(message);
    }
}
