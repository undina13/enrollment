package ru.undina.enrollment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.undina.enrollment.model.SystemItem;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface EnrollmentRepository extends JpaRepository<SystemItem, String> {

    List<SystemItem> getAllByDateBetween(LocalDateTime start, LocalDateTime end);
}
