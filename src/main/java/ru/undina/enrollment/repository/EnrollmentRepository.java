package ru.undina.enrollment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.undina.enrollment.model.SystemItemEntity;

import java.time.LocalDateTime;
import java.util.List;

public interface EnrollmentRepository extends JpaRepository<SystemItemEntity, String> {

    List<SystemItemEntity> getAllByDateBetween(LocalDateTime start, LocalDateTime end);
}
