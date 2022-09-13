package ru.undina.enrollment.controller;

import io.swagger.v3.oas.annotations.Operation;
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

    @Operation(
            summary = "Импортирует элементы файловой системы",
            description = "Элементы импортированные повторно обновляют текущие.\n" +
                    "        Изменение типа элемента с папки на файл и с файла на папку не допускается.\n" +
                    "        Порядок элементов в запросе является произвольным."
    )
    @PostMapping("/imports")
    public void imports(@RequestBody @Valid SystemItemImportRequest itemImportRequest) {
        log.info("create itemImportRequest");
        service.imports(itemImportRequest);
    }

    @Operation(
            summary = "Удалить элемент по идентификатору",
            description = "При удалении папки удаляются все дочерние элементы.\n" +
                    " Доступ к истории обновлений удаленного элемента невозможен."
    )
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable String id, @RequestParam String date) {
        service.delete(id, date);
    }

    @Operation(
            summary = "Получить информацию об элементе по идентификатору",
            description = "При получении информации о папке также предоставляется информация о её дочерних элементах"
    )
    @GetMapping(value = "/nodes/{id}")
    public SystemItemDto getSystemItemDto(@PathVariable String id) {
        return service.getById(id);
    }

    @Operation(
            summary = "Получение истории обновлений",
            description = "Получение списка **файлов**, которые были обновлены за последние 24 часа включительно [date - 24h, date] от времени переданном в запросе"
    )
    @GetMapping(value = "/updates")
    public SystemItemHistoryResponse getUpdates(@RequestParam String date) {
        return service.getByUpdate(date);
    }

    @Operation(
            summary = "Получение истории обновлений по элементу за заданный полуинтервал [from, to)",
            description = "История по удаленным элементам недоступна."
    )
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
