package ru.undina.enrollment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.undina.enrollment.model.SystemItemEntity;
import ru.undina.enrollment.model.SystemItemHistoryEntity;

import java.time.LocalDateTime;
import java.util.List;

public interface SystemItemHistoryRepository extends JpaRepository<SystemItemHistoryEntity, String> {
List<SystemItemHistoryEntity> findAllByDateBetweenAndItemId(LocalDateTime start, LocalDateTime end, String itemId);
    List<SystemItemHistoryEntity> findAllByDateGreaterThanEqualAndDateBeforeAndItemId(LocalDateTime start, LocalDateTime end, String itemId);


    void deleteAllByItemId(String itemId);
}
