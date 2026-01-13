package com.jamersc.springboot.todo.controller;

import com.jamersc.springboot.todo.entity.Status;
import com.jamersc.springboot.todo.entity.Todo;
import com.jamersc.springboot.todo.service.TodoService;
import com.jamersc.springboot.todo.util.ApiResponse;
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

@CrossOrigin(origins = "http://localhost:3000")
@org.springframework.web.bind.annotation.RestController
@RequestMapping("/api/v1/todos")
@AllArgsConstructor
public class TodoController {

    @Autowired
    private TodoService todoService;

    @GetMapping("/")
    public ResponseEntity<ApiResponse<Page<Todo>>> getAllTask(
            @RequestParam(required = false) String search,
            @RequestParam(required = false) Status status,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateFrom,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateTo,
            @PageableDefault(page = 0, size = 10, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable
    ) {
        Page<Todo> todos = todoService.getAllTodos(
                search,
                status,
                dateFrom,
                dateTo,
                pageable
        );

        ApiResponse<Page<Todo>> response = ApiResponse.<Page<Todo>>builder()
                .success(true)
                .message("List of todos retrieved")
                .data(todos)
                .status(HttpStatus.OK.value())
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity.ok(response);
    }

    @PostMapping("/")
    public ResponseEntity<ApiResponse<Todo>> createTodo(@RequestBody Todo todo) {
        Todo createdTodo = todoService.save(todo);
        return ResponseEntity.ok(
                ApiResponse.<Todo>builder()
                        .success(true)
                        .message("New todo created")
                        .data(createdTodo)
                        .status(HttpStatus.CREATED.value())
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Todo>> getTodo(@PathVariable int id) {
        Todo retrievedTodo = todoService.getTodo(id);
        return ResponseEntity.ok(
                ApiResponse.<Todo>builder()
                        .success(true)
                        .message("Todo retrieved")
                        .data(retrievedTodo)
                        .status(HttpStatus.OK.value())
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Todo>> updateTodo(@PathVariable int id, @RequestBody Todo updateTodo) {
        Todo todo = todoService.getTodo(id);

        if (todo != null) {
            todo.setTitle(updateTodo.getTitle());
            todo.setDescription(updateTodo.getDescription());
            todo.setStatus(updateTodo.getStatus());
            Todo updatedTodo = todoService.save(todo);

            return ResponseEntity.ok(
                    ApiResponse.<Todo>builder()
                            .success(true)
                            .message("Todo updated " + id)
                            .data(updatedTodo)
                            .status(HttpStatus.OK.value())
                            .timestamp(LocalDateTime.now())
                            .build()
            );
        }

        throw new RuntimeException("Todo id not found - " + id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deleteTodo(@PathVariable int id) {
        Todo todo = todoService.getTodo(id);

        if (todo != null) {
            todoService.delete(id);
            return ResponseEntity.ok(
                    ApiResponse.<String>builder()
                            .success(true)
                            .message("Todo deleted " + id)
                            .data(null)
                            .status(HttpStatus.NO_CONTENT.value())
                            .timestamp(LocalDateTime.now())
                            .build()
            );
        }

        throw new RuntimeException("Todo id not found - " + id);
    }

}
