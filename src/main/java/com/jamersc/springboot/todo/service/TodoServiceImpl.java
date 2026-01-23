package com.jamersc.springboot.todo.service;

import com.jamersc.springboot.todo.dto.TodoCreateDto;
import com.jamersc.springboot.todo.dto.TodoDto;
import com.jamersc.springboot.todo.dto.TodoUpdateDto;
import com.jamersc.springboot.todo.entity.Status;
import com.jamersc.springboot.todo.entity.Todo;
import com.jamersc.springboot.todo.exception.ResourceNotFoundException;
import com.jamersc.springboot.todo.mapper.TodoMapper;
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
    private final TodoRepository todoRepository;

    public TodoServiceImpl(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @Override
    public Page<TodoDto> getAllTodos(
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

        return todoRepository.findAll(spec, pageable).map(TodoMapper::toDto);
    }

    @Override
    public TodoDto save(TodoCreateDto dto) {
        Todo todo = TodoMapper.toEntity(dto);
        Todo savedTodo = todoRepository.save(todo);
        log.info("Todo {} created successfully!", savedTodo);
        return TodoMapper.toDto(savedTodo);
    }

    @Override
    public TodoDto getTodo(int id) {
        // find a todo using optional (collection)
        Optional<Todo> result = todoRepository.findById(id);
        // create todo empty object
        Todo todo = null;
        if (result.isPresent()) {
            todo = result.get();
        } else {
            log.error("Todo not found with ID: " + id);
            throw new ResourceNotFoundException("Todo id not found - " + id);
        }
        log.info("Todo retrieved successfully with id: {}", id);
        return TodoMapper.toDto(todo);
    }

    @Override
    public TodoDto update(int id, TodoUpdateDto dto) {
        Todo existingTodo = todoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Todo not found with id: " + id));
        existingTodo.setTitle(dto.getTitle());
        existingTodo.setDescription(dto.getDescription());
        existingTodo.setStatus(Status.valueOf(dto.getStatus()));
        Todo updatedTodo = todoRepository.save(existingTodo);
        return TodoMapper.toDto(updatedTodo);
    }

    @Override
    public void delete(int id) {
        Todo existingTodo = todoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Todo not found with id: " + id)
                );
        todoRepository.delete(existingTodo);
        log.info("Todo deleted successfully with id: {}", id);
    }
}
