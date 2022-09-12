package ru.undina.enrollment.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "items")
public class SystemItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @NotNull
    @Column(name = "date", nullable = false)
    private LocalDateTime date;

    //TODO продумать, как лучше, как есть ближе к тз
    // @ManyToOne
    //  @JoinColumn(name = "parent_id", referencedColumnName = "id")
    @Column(name = "parent_id", nullable = false)
    private String parentId;

    @Max(255)
    @Column(name = "url")
    private String url;

    @NotNull
    @Enumerated(EnumType.STRING)
    private SystemItemType type;

    @Column(name = "size")
    private int size;

    @OneToMany(mappedBy = "parentId", fetch = FetchType.LAZY)
    private List<SystemItem> children;
}
