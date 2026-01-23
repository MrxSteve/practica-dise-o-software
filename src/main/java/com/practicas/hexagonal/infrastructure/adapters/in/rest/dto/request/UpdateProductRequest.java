package com.practicas.hexagonal.infrastructure.adapters.in.rest.dto.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record UpdateProductRequest(
        @Size(max = 150, message = "Product name cannot exceed 150 characters")
        String name,
        @Size(max = 50, message = "SKU cannot exceed 50 characters")
        String sku,

        @DecimalMin(value = "0.0", inclusive = true, message = "Price cannot be negative")
        BigDecimal price,

        @Min(value = 0, message = "Stock cannot be negative")
        Integer stock,

        Long categoryId,
        Long brandId
) {
}
