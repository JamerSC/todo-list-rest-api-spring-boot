package com.jamersc.springboot.todo.controller;

import com.jamersc.springboot.todo.entity.Todo;
import com.jamersc.springboot.todo.service.TodoService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@org.springframework.web.bind.annotation.RestController
@RequestMapping("/api")
@AllArgsConstructor
public class RestController {

    @Autowired
    private TodoService todoService;

    @GetMapping("/todos")
    public List<Todo> getAllTask() {
        return todoService.findAll();
    }

    @GetMapping("/todos/{id}")
    public Todo getTodoById(@PathVariable int id) {
        return todoService.findById(id);
    }

    @PostMapping("/todos")
    public Todo createTodo(@RequestBody Todo todo) {
        return todoService.save(todo);
    }

    @PutMapping("/todos")
    public Todo updateTodo(@RequestBody Todo todo) {
        return todoService.save(todo);
    }

    @DeleteMapping("/todos/{id}")
    public String deleteTodoById(@PathVariable int id) {
        todoService.delete(id);
        return "Deleted todo id - " + id;
    }

}
