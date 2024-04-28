package com.banquemisr.challenge05.TaskManagement.service;

import com.banquemisr.challenge05.TaskManagement.model.TaskDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;

public interface TaskService {

    public TaskDto createTask(TaskDto task);

    public Page<TaskDto> getAllTasks(Pageable pageable);

    public TaskDto getTaskById(Long id);

    public TaskDto updateTask(Long id, TaskDto task);

    public void deleteTask(Long id);

    public List<TaskDto> searchTasksByTitle(String title);

    public List<TaskDto> searchTasksByDescription(String description);

    List<TaskDto> searchTasksByStatus(String status);

    public List<TaskDto> searchTasksByDueDate(LocalDate dueDate);

    public List<TaskDto> filterTasks(String title, String description, String status, String priority, LocalDate dueDate);

}
