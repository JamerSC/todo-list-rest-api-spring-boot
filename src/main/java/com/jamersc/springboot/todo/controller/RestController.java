package com.jamersc.springboot.todo.controller;

import com.jamersc.springboot.todo.entity.Todo;
import com.jamersc.springboot.todo.service.TodoService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/api")
@AllArgsConstructor
public class RestController {

    private TodoService todoService;

    @GetMapping("/todos")
    public List<Todo> getAllTask() {
        return todoService.findAll();
    }
}
