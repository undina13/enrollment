package ru.undina.enrollment;

import ru.undina.enrollment.dto.SystemItemImport;
import ru.undina.enrollment.mapper.SystemItemMapper;
import ru.undina.enrollment.model.SystemItemEntity;
import ru.undina.enrollment.model.SystemItemHistoryResponse;
import ru.undina.enrollment.model.SystemItemImportRequest;
import ru.undina.enrollment.model.SystemItemType;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class EnrollmentTestData {
    public static SystemItemImport systemItemImport1 = SystemItemImport.builder()
            .id("someid")
            .url("/file/url15")
            .type(SystemItemType.FILE)
            .size(234)
            .build();

    public static SystemItemImport systemItemImport2 = SystemItemImport.builder()
            .id("item189")
            .type(SystemItemType.FOLDER)
            .build();

    public static SystemItemImport systemItemImport3 = SystemItemImport.builder()
            .id("item18976")
            .type(SystemItemType.FILE)
            .parentId("12345")
            .url("some url")
            .size(50)
            .build();

    public static SystemItemImport systemItemImport4 = SystemItemImport.builder()
            .id("item123")
            .type(SystemItemType.FOLDER)
            .parentId("item2")
            .build();

    public static SystemItemImportRequest systemItemImportRequest1 = SystemItemImportRequest.builder()
            .updateDate(LocalDateTime.of(2022, 9, 10, 21, 12, 1))
            .items(List.of(systemItemImport1, systemItemImport2))
            .build();

    public static SystemItemImportRequest systemItemImportRequest2 = SystemItemImportRequest.builder()
            .updateDate(LocalDateTime.of(2022, 9, 10, 12, 13, 1))
            .items(List.of(systemItemImport3))
            .build();

    public static SystemItemImportRequest systemItemImportRequest3 = SystemItemImportRequest.builder()
            .updateDate(LocalDateTime.of(2022, 9, 10, 21, 14, 1))
            .items(List.of(systemItemImport4))
            .build();

    public static SystemItemImportRequest systemItemImportRequest4 = SystemItemImportRequest.builder()
            .updateDate(LocalDateTime.of(2022, 9, 10, 21, 14, 1))
            .items(List.of(systemItemImport4, systemItemImport4))
            .build();

    public static SystemItemEntity item2 = SystemItemEntity.builder()
            .id("item2")
            .parentId("item1")
            .url("/file/url15")
            .type(SystemItemType.FILE)
            .size(100)
            .date(LocalDateTime.parse("2022-09-10 22:12:01", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
            .build();

    public static SystemItemEntity item3 = SystemItemEntity.builder()
            .id("item3")
            .parentId("item1")
            .url("/file/url15h")
            .type(SystemItemType.FILE)
            .size(100)
            .date(LocalDateTime.parse("2022-09-11 00:12:01", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
            .build();

    public static SystemItemEntity item1 = SystemItemEntity.builder()
            .id("item1")
            .type(SystemItemType.FOLDER)
            .children(List.of(item2, item3))
            .date(LocalDateTime.parse("2022-09-11 00:12:01", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
            .build();


    public static SystemItemHistoryResponse response1 = new SystemItemHistoryResponse(List
            .of(SystemItemMapper.toSystemItemHistoryUnit(item3)));
}
