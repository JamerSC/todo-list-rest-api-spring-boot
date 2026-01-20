package com.jamersc.springboot.todo.exception;

// Custom Business Exceptions
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
