package com.example.BookMyShowSpringBootApplication.exception;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
