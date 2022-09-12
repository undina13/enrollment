package ru.undina.enrollment.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.undina.enrollment.service.EnrollmentService;

@RestController
@RequestMapping()
@Slf4j
public class EnrollmentController {
    private final EnrollmentService service;

    @Autowired
    public EnrollmentController(EnrollmentService service) {
        this.service = service;
    }


//    @PostMapping("/imports")
//    SystemItemImportRequest create( @RequestBody @Valid SystemItemImportRequest itemImportRequest) {
//        log.info("create itemImportRequest");
//        return service.create(itemImportRequest);
//    }

}
