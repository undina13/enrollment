package ru.undina.enrollment;

import ru.undina.enrollment.dto.SystemItemImport;
import ru.undina.enrollment.model.SystemItem;
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
            .build();

    public static SystemItemImport systemItemImport4 = SystemItemImport.builder()
            .id("item123")
            .type(SystemItemType.FOLDER)
            .parentId("item2")
            .build();

    public static SystemItemImportRequest systemItemImportRequest1 = SystemItemImportRequest.builder()
            .updateDate("2022-09-10T21:12:01.000Z")
            .items(List.of(systemItemImport1, systemItemImport2))
            .build();

    public static SystemItemImportRequest systemItemImportRequest2 = SystemItemImportRequest.builder()
            .updateDate("2022-09-10T21:13:01.000Z")
            .items(List.of(systemItemImport3))
            .build();

    public static SystemItemImportRequest systemItemImportRequest3 = SystemItemImportRequest.builder()
            .updateDate("2022-09-10T21:14:01.000Z")
            .items(List.of(systemItemImport4))
            .build();

    public static SystemItemImportRequest systemItemImportRequest4 = SystemItemImportRequest.builder()
            .updateDate("2022-09-10T21:14:01.000Z")
            .items(List.of(systemItemImport4, systemItemImport4))
            .build();

    public static SystemItem item2 = SystemItem.builder()
            .id("item2")
            .parentId("item1")
            .url("/file/url15")
            .type(SystemItemType.FILE)
            .size(100)
            .date(LocalDateTime.parse("2022-09-10T22:12:01.000Z", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'")))
            .build();

    public static SystemItem item3 = SystemItem.builder()
            .id("item3")
            .parentId("item1")
            .url("/file/url15h")
            .type(SystemItemType.FILE)
            .size(100)
            .date(LocalDateTime.parse("2022-09-11T00:12:01.000Z", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'")))
            .build();

    public static  SystemItem item1 = SystemItem.builder()
            .id("item1")
            .type(SystemItemType.FOLDER)
            .children(List.of(item2, item3))
            .date(LocalDateTime.parse("2022-09-11T00:12:01.000Z", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'")))
            .build();

}
