package com.practicas.hexagonal.infrastructure.adapters.in.rest.dto.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record ProductResponse(
        Long id,
        String name,
        String sku,
        BigDecimal price,
        Integer stock,

        String categoryName,
        String brandName,
        
        LocalDateTime createdAt
) {
}
