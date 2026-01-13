package com.jamersc.springboot.todo.service;

import com.jamersc.springboot.todo.entity.Status;
import com.jamersc.springboot.todo.entity.Todo;
import com.jamersc.springboot.todo.repository.TodoRepository;
import com.jamersc.springboot.todo.repository.TodoSpecification;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class TodoServiceImpl implements TodoService {
    @Autowired
    private TodoRepository todoRepository;
    @Override
    public Page<Todo> getAllTodos(
            String search,
            Status status,
            LocalDate dateFrom,
            LocalDate dateTo,
            Pageable pageable
    ) {

        Specification<Todo> spec = Specification.allOf(
                TodoSpecification.search(search),
                TodoSpecification.hasStatus(status),
                TodoSpecification.dateRange(dateFrom, dateTo)
        );

        return todoRepository.findAll(spec, pageable);
    }

    @Override
    public Todo getTodo(int id) {
        // find a todo using optional (collection)
        Optional<Todo> result = todoRepository.findById(id);
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
        return todoRepository.save(todo);
    }

    @Override
    public void delete(int id) {
        Optional<Todo> result = todoRepository.findById(id);
        Todo todo = null;
        if (result.isPresent()) {
            todo = result.get();
        } else {
            throw new RuntimeException("Todo id not found - " + id);
        }
        todoRepository.delete(todo);
    }
}
