package com.jamersc.springboot.todo.service;

import com.jamersc.springboot.todo.entity.Todo;

import java.util.List;

public interface TodoService {
    List<Todo> findAll();
    Todo findById(int id);
    Todo save(Todo todo);
    void delete(int id);
}
