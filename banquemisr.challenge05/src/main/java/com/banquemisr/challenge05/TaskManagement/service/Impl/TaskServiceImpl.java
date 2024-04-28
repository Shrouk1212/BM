package com.banquemisr.challenge05.TaskManagement.service.Impl;

import com.banquemisr.challenge05.TaskManagement.Entity.Task;
import com.banquemisr.challenge05.TaskManagement.exception.ResourceNotFoundException;
import com.banquemisr.challenge05.TaskManagement.mapper.TaskMapper;
import com.banquemisr.challenge05.TaskManagement.model.TaskDto;
import com.banquemisr.challenge05.TaskManagement.repository.TaskRepository;
import com.banquemisr.challenge05.TaskManagement.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService {
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private TaskMapper taskMapper;

    public TaskDto createTask(TaskDto taskDto) {
        return taskMapper.convertTaskEntityToDto(taskRepository.save(taskMapper.convertTaskDtoToEntity(taskDto)));
    }

    public Page<TaskDto> getAllTasks(Pageable pageable) {
        return taskRepository.findAll(pageable).map(taskMapper::convertTaskEntityToDto);
    }

    public TaskDto getTaskById(Long id) {
        Optional<Task> taskOptional = taskRepository.findById(id);
        Task task = taskOptional.orElseThrow(() -> new ResourceNotFoundException("Task not found with id: " + id));
        return taskMapper.convertTaskEntityToDto(task);
    }

    public TaskDto updateTask(Long id, TaskDto taskDto) {
        Optional<Task> taskOptional = taskRepository.findById(id);
        Task task = taskOptional.orElseThrow(() -> new ResourceNotFoundException("Task not found with id: " + id));

        // Update task fields
        task.setTitle(taskDto.getTitle());
        task.setStatus(taskDto.getStatus());
        task.setPriority(taskDto.getPriority());
        task.setDescription(taskDto.getDescription());
        task.setDueDate(taskDto.getDueDate());

        return taskMapper.convertTaskEntityToDto(taskRepository.save(task));
    }

    public void deleteTask(Long id) {
        Optional<Task> taskOptional = taskRepository.findById(id);
        Task task = taskOptional.orElseThrow(() -> new ResourceNotFoundException("Task not found with id: " + id));
        taskRepository.delete(task);
    }

    public List<TaskDto> searchTasksByTitle(String title) {
        // Validate title (optional)
        if (title == null || title.isEmpty()) {
            throw new IllegalArgumentException("Title must not be blank");
        }

        // Call repository or perform search logic
        return taskMapper.convertTaskEntityListToDtoList(taskRepository.findByTitleContainingIgnoreCase(title));
    }

    @Override
    public List<TaskDto> searchTasksByDescription(String description) {
        // Validate description (optional)
        if (description == null || description.isEmpty()) {
            throw new IllegalArgumentException("Description must not be blank");
        }

        // Call repository or perform search logic
        return taskMapper.convertTaskEntityListToDtoList(taskRepository.findByDescriptionContainingIgnoreCase(description));
    }

    public List<TaskDto> searchTasksByStatus(String status) {
        // Call the repository method to find tasks by status
        List<Task> tasks = taskRepository.findByStatus(status);
        // Convert Task entities to TaskDto objects and return
        return taskMapper.convertTaskEntityListToDtoList(tasks);
    }

    @Override
    public List<TaskDto> searchTasksByDueDate(LocalDate dueDate) {
        // Call the repository method to find tasks by due date
        List<Task> tasks = taskRepository.findByDueDate(dueDate);
        // Convert Task entities to TaskDto objects and return
        return taskMapper.convertTaskEntityListToDtoList(tasks);
    }

    @Override
    public List<TaskDto> filterTasks(String title, String description, String status, String priority, LocalDate dueDate) {
        // Call the repository method to retrieve all tasks
        List<Task> tasks = taskRepository.findAll();

        // Apply filters based on query parameters
        if (title != null) {
            tasks = tasks.stream().filter(task -> task.getTitle().contains(title)).collect(Collectors.toList());
        }
        if (description != null) {
            tasks = tasks.stream().filter(task -> task.getDescription().contains(description)).collect(Collectors.toList());
        }
        if (status != null) {
            tasks = tasks.stream().filter(task -> task.getStatus().equals(status)).collect(Collectors.toList());
        }
        // Apply similar logic for other filtering criteria

        // Convert Task entities to TaskDto objects and return
        return taskMapper.convertTaskEntityListToDtoList(tasks);
    }

}
