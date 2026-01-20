package com.jamersc.springboot.todo.mapper;

import com.jamersc.springboot.todo.dto.TodoCreateDto;
import com.jamersc.springboot.todo.dto.TodoDto;
import com.jamersc.springboot.todo.dto.TodoUpdateDto;
import com.jamersc.springboot.todo.entity.Status;
import com.jamersc.springboot.todo.entity.Todo;

public class TodoMapper {

    public static TodoDto toDto(Todo todo) {
        if (todo == null) return null;

        return TodoDto.builder()
                .id(todo.getId())
                .title(todo.getTitle())
                .description(todo.getDescription())
                .status(todo.getStatus())
                .build();
    }

    public static Todo toEntity(TodoCreateDto createDto) {
        Todo todo = new Todo();
        todo.setTitle(createDto.getTitle());
        todo.setDescription(createDto.getDescription());
        todo.setStatus(Status.valueOf(createDto.getStatus()));
        return todo;
    }

    public static void updateEntity(Todo todo, TodoUpdateDto dto) {
        todo.setTitle(dto.getTitle());
        todo.setDescription(dto.getDescription());
        todo.setStatus(Status.valueOf(dto.getStatus()));
    }
}
