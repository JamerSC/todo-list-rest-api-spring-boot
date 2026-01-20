package com.jamersc.springboot.todo.exception;

// Bad Request (Invalid Enum, Logic Error)
public class BadRequestException extends RuntimeException {
    public BadRequestException(String message) {
        super(message);
    }
}
