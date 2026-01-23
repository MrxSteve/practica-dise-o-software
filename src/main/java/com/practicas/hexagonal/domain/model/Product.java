package com.practicas.hexagonal.domain.model;

import com.practicas.hexagonal.domain.exception.InvalidEntityException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class Product {
    private Long id;
    private String name;
    private String sku;
    private BigDecimal price;
    private Integer stock;

    private Long categoryId;
    private Long brandId;
    
    private LocalDateTime createdAt;

    public void validateName() {
        if (name == null || name.isBlank()) {
            throw new InvalidEntityException("Product name cannot be null or empty");
        }
        
        if (name.length() > 150) {
            throw new InvalidEntityException("Product name cannot exceed 150 characters");
        }
    }

    public void validateSku() {
        if (sku == null || sku.isBlank()) {
            throw new InvalidEntityException("Product SKU cannot be null or empty");
        }
        
        if (sku.length() > 50) {
            throw new InvalidEntityException("Product SKU cannot exceed 50 characters");
        }
    }

    public void validatePrice() {
        if (price == null) {
            throw new InvalidEntityException("Product price cannot be null");
        }
        
        if (price.compareTo(BigDecimal.ZERO) < 0) {
            throw new InvalidEntityException("Product price cannot be negative");
        }
    }

    public void validateStock() {
        if (stock == null) {
            throw new InvalidEntityException("Product stock cannot be null");
        }
        
        if (stock < 0) {
            throw new InvalidEntityException("Product stock cannot be negative");
        }
    }

    public void validateCategoryId() {
        if (categoryId == null) {
            throw new InvalidEntityException("Product category ID cannot be null");
        }
    }

    public void validateBrandId() {
        if (brandId == null) {
            throw new InvalidEntityException("Product brand ID cannot be null");
        }
    }

    public void validate() {
        validateName();
        validateSku();
        validatePrice();
        validateStock();
        validateCategoryId();
        validateBrandId();
    }
}
