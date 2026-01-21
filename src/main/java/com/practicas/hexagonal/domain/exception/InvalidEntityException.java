package com.practicas.hexagonal.domain.exception;

public class InvalidEntityException extends RuntimeException {
    public InvalidEntityException(String message) {
        super(message);
    }
    
    public InvalidEntityException(String entityName, String reason) {
        super("Invalid " + entityName + ": " + reason);
    }
}
