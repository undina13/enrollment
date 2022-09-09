package ru.undina.enrollment.schemas;

import java.time.LocalDateTime;
import java.util.List;

public class SystemItem {
    private String id;
    private LocalDateTime date;
    private String parentId;
    private String url;
    private SystemItemType type;
    private int size;
    private List<SystemItem> children;
}
