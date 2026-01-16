package com.jamersc.springboot.todo.service;

import com.jamersc.springboot.todo.entity.Status;
import com.jamersc.springboot.todo.entity.Todo;
import com.jamersc.springboot.todo.repository.TodoRepository;
import com.jamersc.springboot.todo.repository.TodoSpecification;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
@Transactional
public class TodoServiceImpl implements TodoService {

    private static final Logger log = LoggerFactory.getLogger(TodoServiceImpl.class);

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
        log.info("Fetching all todos filters : search={}, status={}, dateFrom={}, dateTo={}, pageable={}",
                search, status, dateFrom, dateTo, pageable);

//        log.debug("Fetching all todos filters : search={}, status={}, dateFrom={}, dateTo={}, pageable={}",
//                search, status, dateFrom, dateTo, pageable);

        Specification<Todo> spec = Specification.allOf(
                TodoSpecification.search(search),
                TodoSpecification.hasStatus(status),
                TodoSpecification.dateRange(dateFrom, dateTo)
        );

        return todoRepository.findAll(spec, pageable);
    }

    @Override
    public Todo save(Todo todo) {
        Todo createdTodo = todoRepository.save(todo);
        log.info("Todo {} created/updated successfully!", createdTodo);
        return createdTodo;
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
            log.error("Todo not found with ID: " + id);
            throw new RuntimeException("Todo id not found - " + id);
        }
        log.info("Todo retrieved successfully with id: {}", id);
        return todo;
    }

    @Override
    public void delete(int id) {
        Optional<Todo> result = todoRepository.findById(id);
        Todo todo = null;
        if (result.isPresent()) {
            todo = result.get();
        } else {
            log.error("Todo not found with ID: " + id);
            throw new RuntimeException("Todo id not found - " + id);
        }
        log.info("Todo deleted successfully with id: {}", id);
        todoRepository.delete(todo);
    }
}
