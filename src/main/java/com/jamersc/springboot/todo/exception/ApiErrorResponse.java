package com.jamersc.springboot.todo.exception;

import lombok.Builder;
import lombok.Data;

import java.time.OffsetDateTime;
import java.util.List;

@Data
@Builder
public class ApiErrorResponse {

//    Standard API Error Response
//    This is the single error format your frontend will rely on.

    private int status;
    private String error;
    private String message;
    private String path;
    private OffsetDateTime timestamp;

    // optional (for validation)
    private List<FieldError> fieldErrors;

    @Data
    @Builder
    public static class FieldError {
        private String field;
        private String message;
    }
}
