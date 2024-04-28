package com.banquemisr.challenge05.TaskManagement.repository;

import com.banquemisr.challenge05.TaskManagement.Entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByTitleContainingIgnoreCase(String title);
    List<Task> findByDescriptionContainingIgnoreCase(String description);

    List<Task> findByStatus(String status);

    List<Task> findByDueDate(LocalDate dueDate);
}
