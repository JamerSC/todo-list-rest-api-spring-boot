package com.jamersc.springboot.todo.repository;

import com.jamersc.springboot.todo.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRespository extends JpaRepository<Todo, Integer> {
    // custom query here
}
