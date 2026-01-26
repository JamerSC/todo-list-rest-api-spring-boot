package com.jamersc.springboot.todo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jamersc.springboot.todo.entity.Status;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

import java.time.OffsetDateTime;

@Data
@Builder
public class TodoDto {
    private int id;
    private String title;
    private String description;
    @Enumerated(EnumType.STRING)
    private Status status;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ssXXX")
    private OffsetDateTime createdAt;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ssXXX")
    private OffsetDateTime updatedAt;
}
