package com.jamersc.springboot.todo.repository;

import com.jamersc.springboot.todo.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface TodoRepository extends JpaRepository<Todo, Integer>,
        JpaSpecificationExecutor<Todo>
{
    // custom query here
}
