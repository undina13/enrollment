package ru.undina.enrollment.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "items")
public class SystemItem implements Serializable {
    @Id
    @Column(name = "id", nullable = false)
    private UUID id;

    @NotNull
    @Column(name = "date", nullable = false)
    private String date;


    @Column(name = "parent_id", nullable = false)
    private UUID parentId;


    @Column(name = "url")
    private String url;

    @NotNull
    @Enumerated(EnumType.STRING)
    private SystemItemType type;

    @Column(name = "size")
    private Integer size;

 //  @OneToMany(mappedBy = "parentId", fetch = FetchType.LAZY)
   @OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
   @JoinColumn(name = "parent_id", referencedColumnName = "id")
    private List<SystemItem> children;
}
