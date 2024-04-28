package com.banquemisr.challenge05.TaskManagement.mapper;

import com.banquemisr.challenge05.TaskManagement.Entity.Task;
import com.banquemisr.challenge05.TaskManagement.model.TaskDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TaskMapper {
    @Autowired
    private ModelMapper modelMapper;

    public Task convertTaskDtoToEntity(TaskDto taskDto) {
        return modelMapper.map(taskDto, Task.class);
    }

    public TaskDto convertTaskEntityToDto(Task task) {
        return modelMapper.map(task, TaskDto.class);
    }

    public List<Task> convertTaskDtoListToEntityList(List<TaskDto> taskDtoList) {
        return taskDtoList.stream()
                .map(this::convertTaskDtoToEntity)
                .collect(Collectors.toList());
    }

    public List<TaskDto> convertTaskEntityListToDtoList(List<Task> taskList) {
        return taskList.stream()
                .map(this::convertTaskEntityToDto)
                .collect(Collectors.toList());
    }


}
