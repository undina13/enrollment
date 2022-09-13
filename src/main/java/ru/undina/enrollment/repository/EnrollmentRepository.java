package ru.undina.enrollment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.undina.enrollment.model.SystemItem;

import java.util.Optional;
import java.util.UUID;

public interface EnrollmentRepository extends JpaRepository<SystemItem, String> {

    Optional<SystemItem> findById(UUID s);
}
