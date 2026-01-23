package com.practicas.hexagonal.domain.exception;

public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(String entityName, Long id) {
        super(entityName + " not found with id: " + id);
    }

    public EntityNotFoundException(String entityName, String fieldName, String value) {
        super(entityName + " not found with " + fieldName + ": " + value);
    }
    
    public EntityNotFoundException(String message) {
        super(message);
    }
}
