package com.banquemisr.challenge05.TaskManagement.model;
import lombok.Data;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import java.time.LocalDate;
@Data
public class TaskDto {
    @NotBlank(message = "Title is required")
    private String title;

    @NotBlank(message = "Description is required")
    private String description;

    @NotBlank(message = "Status is required")
    private String status;

    @NotBlank(message = "Priority is required")
    @Pattern(regexp = "^(Low|Medium|High)$", message = "Priority must be Low, Medium, or High")
    private String priority;

    @NotNull(message = "Due date is required")
    private LocalDate dueDate;
}
