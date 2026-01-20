package com.jamersc.springboot.todo.service;

import com.jamersc.springboot.todo.dto.TodoCreateDto;
import com.jamersc.springboot.todo.dto.TodoDto;
import com.jamersc.springboot.todo.dto.TodoUpdateDto;
import com.jamersc.springboot.todo.entity.Status;
import com.jamersc.springboot.todo.entity.Todo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;

public interface TodoService {
    Page<TodoDto> getAllTodos(
            String search,
            Status status,
            LocalDate dateFrom,
            LocalDate dateTo,
            Pageable pageable
    );
    TodoDto save(TodoCreateDto dto);
    TodoDto update(int id, TodoUpdateDto dto);
    TodoDto getTodo(int id);
    void delete(int id);
}
