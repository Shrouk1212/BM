package com.banquemisr.challenge05.TaskManagement.controller;

import com.banquemisr.challenge05.TaskManagement.model.TaskDto;
import com.banquemisr.challenge05.TaskManagement.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/tasks")
@Validated
public class TaskManagementController {

    @Autowired
    private TaskService taskService;

    @PostMapping
    public ResponseEntity<?> createTask(@Valid @RequestBody TaskDto task) {
        TaskDto createdTask = taskService.createTask(task);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTask);
    }

//    @GetMapping
//    public List<TaskDto> getAllTasks() {
//        return taskService.getAllTasks();
//    }
@GetMapping
    public ResponseEntity
            <?> getAllTasks(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortOrder) {

        Page<TaskDto> taskPage = taskService.getAllTasks(PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(sortOrder), sortBy)));
        List<TaskDto> tasks = taskPage.getContent();

        Map<String, Object> response = new HashMap<>();
        response.put("tasks", tasks);
        response.put("currentPage", taskPage.getNumber());
        response.put("totalItems", taskPage.getTotalElements());
        response.put("totalPages", taskPage.getTotalPages());

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> getTaskById(@PathVariable Long id) {
        TaskDto task = taskService.getTaskById(id);
        if (task == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(task);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateTask(@PathVariable Long id, @Valid @RequestBody TaskDto task) {
        TaskDto updatedTask = taskService.updateTask(id, task);
        if (updatedTask == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedTask);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }

    //Search Apis :
    // Endpoint to search tasks by title
    @GetMapping("/searchByTitle")
    public List<TaskDto> searchTasksByTitle(@RequestParam @NotBlank(message = "Title must not be blank") String title) {
        return taskService.searchTasksByTitle(title);
    }

    // Endpoint to search tasks by description
    @GetMapping("/searchByDescription")
    public List<TaskDto> searchTasksByDescription(@RequestParam @NotBlank(message = "Description must not be blank") String description) {
        return taskService.searchTasksByDescription(description);
    }

    // Endpoint to search tasks by status
    @GetMapping("/searchByStatus")
    public List<TaskDto> searchTasksByStatus(@RequestParam String status) {
        return taskService.searchTasksByStatus(status);
    }

    // Endpoint to search tasks by due date
    @GetMapping("/searchByDueDate")
    public List<TaskDto> searchTasksByDueDate(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dueDate) {
        return taskService.searchTasksByDueDate(dueDate);
    }

    @GetMapping("/filter")
    // @PreAuthorize("hasRole('ADMIN')")
    public List<TaskDto> filterTasks(@RequestParam(required = false) String title,
                                     @RequestParam(required = false) String description,
                                     @RequestParam(required = false) String status,
                                     @RequestParam(required = false) String priority,
                                     @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dueDate) {
        return taskService.filterTasks(title, description, status, priority, dueDate);
    }
}
