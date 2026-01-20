package com.jamersc.springboot.todo.dto;

import com.jamersc.springboot.todo.entity.Status;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TodoDto {
    private int id;
    private String title;
    private String description;
    @Enumerated(EnumType.STRING)
    private Status status;
}
