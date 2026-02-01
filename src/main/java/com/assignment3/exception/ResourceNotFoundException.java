package com.assignment3.exception;

public class ResourceNotFoundException extends InvalidInputException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
