package com.jamersc.springboot.todo.controller;

import com.jamersc.springboot.todo.dto.TodoCreateDto;
import com.jamersc.springboot.todo.dto.TodoDto;
import com.jamersc.springboot.todo.dto.TodoUpdateDto;
import com.jamersc.springboot.todo.entity.Status;
import com.jamersc.springboot.todo.entity.Todo;
import com.jamersc.springboot.todo.service.TodoService;
import com.jamersc.springboot.todo.util.ApiResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@CrossOrigin(origins = "http://localhost:3000") // todo web react localhost ip
@org.springframework.web.bind.annotation.RestController
@RequestMapping("/api/v1/todos")
@AllArgsConstructor
public class TodoController {

    @Autowired
    private TodoService todoService;

    @GetMapping("/")
    public ResponseEntity<ApiResponse<Page<TodoDto>>> getAllTask(
            @RequestParam(required = false) String search,
            @RequestParam(required = false) Status status,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateFrom,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateTo,
            @PageableDefault(page = 0, size = 10, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable
    ) {
        Page<TodoDto> todos = todoService.getAllTodos(
                search,
                status,
                dateFrom,
                dateTo,
                pageable
        );

        ApiResponse<Page<TodoDto>> response = ApiResponse.<Page<TodoDto>>builder()
                .success(true)
                .message("List of todos retrieved successfully")
                .data(todos)
                .status(HttpStatus.OK.value())
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity.ok(response);
    }

    @PostMapping("/")
    public ResponseEntity<ApiResponse<TodoDto>> createTodo(
            @Valid @RequestBody TodoCreateDto dto
    ) {
        TodoDto createdTodo = todoService.save(dto);
        return ResponseEntity.ok(
                ApiResponse.<TodoDto>builder()
                        .success(true)
                        .message("Todo created successfully")
                        .data(createdTodo)
                        .status(HttpStatus.CREATED.value())
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<TodoDto>> getTodo(
            @PathVariable int id
    ) {
        TodoDto retrievedTodo = todoService.getTodo(id);
        return ResponseEntity.ok(
                ApiResponse.<TodoDto>builder()
                        .success(true)
                        .message("Todo retrieved successfully")
                        .data(retrievedTodo)
                        .status(HttpStatus.OK.value())
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<TodoDto>> updateTodo(
            @PathVariable int id,
            @Valid @RequestBody TodoUpdateDto dto)
    {
        TodoDto updatedTodo = todoService.update(id, dto);
            return ResponseEntity.ok(
                    ApiResponse.<TodoDto>builder()
                            .success(true)
                            .message("Todo updated successfully!")
                            .data(updatedTodo)
                            .status(HttpStatus.OK.value())
                            .timestamp(LocalDateTime.now())
                            .build()
            );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deleteTodo(@PathVariable int id) {
        TodoDto retrievedTodo = todoService.getTodo(id);
        todoService.delete(id);

        return ResponseEntity.ok(
                ApiResponse.<String>builder()
                        .success(true)
                        .message("Todo deleted successfully")
                        .data(String.valueOf(retrievedTodo))
                        .status(HttpStatus.OK.value())
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }

}
