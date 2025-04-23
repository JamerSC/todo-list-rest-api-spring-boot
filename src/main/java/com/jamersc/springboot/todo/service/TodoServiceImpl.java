package com.jamersc.springboot.todo.service;

import com.jamersc.springboot.todo.entity.Todo;
import com.jamersc.springboot.todo.repository.TodoRespository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class TodoServiceImpl implements TodoService {
    @Autowired
    private TodoRespository todoRespository;
    @Override
    public List<Todo> findAll() {
        List<Todo> todos = todoRespository.findAll();
        todos.forEach(System.out::println);
        return todos;
    }

    @Override
    public Todo findById(int id) {
        // find a todo using optional (collection)
        Optional<Todo> result = todoRespository.findById(id);
        // create todo empty object
        Todo todo = null;
        if (result.isPresent()) {
            todo = result.get();
        } else {
            throw new RuntimeException("Todo id not found - " + id);
        }
        return todo;
    }

    @Override
    public Todo save(Todo todo) {
        return todoRespository.save(todo);
    }

    @Override
    public void delete(int id) {
        Optional<Todo> result = todoRespository.findById(id);
        Todo todo = null;
        if (result.isPresent()) {
            todo = result.get();
        } else {
            throw new RuntimeException("Todo id not found - " + id);
        }
        todoRespository.delete(todo);
    }
}
