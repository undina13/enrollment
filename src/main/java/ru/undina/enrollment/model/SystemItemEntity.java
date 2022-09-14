package ru.undina.enrollment.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "items")
public class SystemItemEntity implements Serializable {
    @Id
    @Column(name = "id", nullable = false)
    private String id;

    @NotNull
    @Column(name = "date", nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    private LocalDateTime date;


    @Column(name = "parent_id", nullable = false)
    private String parentId;


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
    private List<SystemItemEntity> children;
}
