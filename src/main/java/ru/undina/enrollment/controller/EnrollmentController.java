package ru.undina.enrollment.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.undina.enrollment.dto.SystemItemDto;
import ru.undina.enrollment.exception.BadRequestException;
import ru.undina.enrollment.exception.ItemNotFoundException;
import ru.undina.enrollment.model.Error;
import ru.undina.enrollment.model.SystemItemHistoryResponse;
import ru.undina.enrollment.model.SystemItemImportRequest;
import ru.undina.enrollment.service.EnrollmentService;

import javax.validation.Valid;

@RestController
@RequestMapping()
@Slf4j
public class EnrollmentController {
    private final EnrollmentService service;

    @Autowired
    public EnrollmentController(EnrollmentService service) {
        this.service = service;
    }


    @PostMapping("/imports")
    public void imports(@RequestBody @Valid SystemItemImportRequest itemImportRequest) {
        log.info("create itemImportRequest");
        service.imports(itemImportRequest);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable String id, @RequestParam String date) {
        service.delete(id, date);
    }

    @GetMapping(value = "/nodes/{id}")
    public SystemItemDto getSystemItemDto(@PathVariable String id) {
        return service.getById(id);
    }

    @GetMapping(value = "/updates")
    public SystemItemHistoryResponse getUpdates(@RequestParam String date) {
        return service.getByUpdate(date);
    }

    @GetMapping(value = "/nodes/{id}/history")
    public SystemItemHistoryResponse getHistory(
            @PathVariable String id,
            @RequestParam(required = false) String dateStart,
            @RequestParam(required = false) String dateEnd) {
        return service.getHistory(id, dateStart, dateEnd);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BadRequestException.class)
    public Error handleBadRequestException(BadRequestException e) {
        return new Error(404, e.getMessage());
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ItemNotFoundException.class)
    public Error handleNotFoundException(ItemNotFoundException e) {
        return new Error(400, e.getMessage());
    }
}
