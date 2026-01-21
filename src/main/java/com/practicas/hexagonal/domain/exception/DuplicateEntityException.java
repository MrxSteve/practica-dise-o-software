package com.practicas.hexagonal.domain.exception;

public class DuplicateEntityException extends RuntimeException {
    public DuplicateEntityException(String entityName, String fieldName, String value) {
        super(entityName + " already exists with " + fieldName + ": " + value);
    }
    
    public DuplicateEntityException(String message) {
        super(message);
    }
}
