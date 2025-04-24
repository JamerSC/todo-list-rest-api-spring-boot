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

    /*@PutMapping("/todos")
    public Todo updateTodo(@RequestBody Todo todo) {
        return todoService.save(todo);
    }*/

    // working - id is not replaceable - throws error
    @PutMapping("/todos/{id}")
    public Todo updateTodo(@PathVariable int id, @RequestBody Todo updateTodo) {
        Todo todo = todoService.findById(id);
        if (todo != null) {
            todo.setTitle(updateTodo.getTitle());
            todo.setDescription(updateTodo.getDescription());
            todo.setStatus(updateTodo.getStatus());
            return todoService.save(todo);
        }
        throw new RuntimeException("Todo id not found - " + id);
    }

    @DeleteMapping("/todos/{id}")
    public String deleteTodoById(@PathVariable int id) {
        todoService.delete(id);
        return "Deleted todo id - " + id;
    }

}
