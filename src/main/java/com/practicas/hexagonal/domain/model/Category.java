package com.practicas.hexagonal.domain.model;

import com.practicas.hexagonal.domain.exception.InvalidEntityException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class Category {
    private Long id;
    private String name;

    public void validateName() {
        if (name == null || name.isBlank()) {
            throw new InvalidEntityException("Category name cannot be null or empty");
        }
        
        if (name.length() > 100) {
            throw new InvalidEntityException("Category name cannot exceed 100 characters");
        }
    }

    public void validate() {
        validateName();
    }
}
