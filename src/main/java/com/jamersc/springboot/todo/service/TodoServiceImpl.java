package com.jamersc.springboot.todo.service;

import com.jamersc.springboot.todo.entity.Todo;
import com.jamersc.springboot.todo.repository.TodoRespository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class TodoServiceImpl implements TodoService {
    @Autowired
    private TodoRespository todoRespository;
    @Override
    public List<Todo> findAll() {
        return todoRespository.findAll();
    }
}
