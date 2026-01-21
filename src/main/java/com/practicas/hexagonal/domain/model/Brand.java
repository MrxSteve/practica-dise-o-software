package com.practicas.hexagonal.domain.model;

import com.practicas.hexagonal.domain.exception.InvalidEntityException;
import lombok.*;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class Brand {
    private Long id;
    private String name;

    public void validateName() {
        if (name == null) {
            throw new InvalidEntityException("Brand name cannot be null");
        }

        if (name.length() > 100) {
            throw new InvalidEntityException("Brand name cannot exceed 100 characters");
        }
    }

    public void validate() {
        validateName();
    }
}
