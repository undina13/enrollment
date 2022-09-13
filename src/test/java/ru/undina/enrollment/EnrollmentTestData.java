//package ru.undina.enrollment;
//
//import ru.undina.enrollment.dto.SystemItemImport;
//import ru.undina.enrollment.model.SystemItem;
//import ru.undina.enrollment.model.SystemItemImportRequest;
//import ru.undina.enrollment.model.SystemItemType;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class EnrollmentTestData {
//    public static SystemItemImport systemItemImport1 = SystemItemImport.builder()
//            .
//            //.parentId(systemItemImport.getParentId())
//            .url("/file/url15")
//            .type(SystemItemType.FILE)
//            .size(234)
//            .build();
//
//    public static SystemItemImport systemItemImport2 = SystemItemImport.builder()
//            .id("item189")
//            //.parentId(systemItemImport.getParentId())
//            .type(SystemItemType.FOLDER)
//            .build();
//
//    public static SystemItemImportRequest systemItemImportRequest1 = SystemItemImportRequest.builder()
//            .updateDate("2022-09-10T21:12:01.000Z")
//            .items(List.of(systemItemImport1, systemItemImport2))
//            .build();
//
//
//
//    public static SystemItem item2 = SystemItem.builder()
//            .id("item2")
//            .parentId("item1")
//            .url("/file/url15")
//            .type(SystemItemType.FILE)
//            .size(100)
//          //   .children(new ArrayList<>())
//            .date("2022-09-10T22:12:01.000Z")
//            .build();
//
//    public static SystemItem item3 = SystemItem.builder()
//            .id("item3")
//            .parentId("item1")
//            .url("/file/url15h")
//            .type(SystemItemType.FILE)
//            .size(100)
//             .children(new ArrayList<>())
//            .date("2022-09-11T00:12:01.000Z")
//            .build();
//
//    public static  SystemItem item1 = SystemItem.builder()
//            .id("item1")
//            // .parentId(systemItemImport.getParentId())
//            //  .url(systemItemImport.getUrl())
//            .type(SystemItemType.FOLDER)
//            //  .size(systemItemImport.getSize())
//            .children(List.of(item2, item3))
//            .date("2022-09-10T21:12:01.000Z")
//            .build();
//
//}
