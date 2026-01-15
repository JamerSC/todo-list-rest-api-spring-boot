package com.jamersc.springboot.todo.service;

import com.jamersc.springboot.todo.entity.Status;
import com.jamersc.springboot.todo.entity.Todo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;

public interface TodoService {
    Page<Todo> getAllTodos(
            String search,
            Status status,
            LocalDate dateFrom,
            LocalDate dateTo,
            Pageable pageable
    );
    Todo save(Todo todo);
    Todo getTodo(int id);
    void delete(int id);
}
