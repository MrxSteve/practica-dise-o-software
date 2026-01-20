package com.practicas.hexagonal.infrastructure.exception;

import java.time.LocalDateTime;

/**
 * Estructura de respuesta de error estandarizada
 */
public record ErrorResponse(
        String message,
        int status,
        LocalDateTime timestamp
) {
    public ErrorResponse(String message, int status) {
        this(message, status, LocalDateTime.now());
    }
}
