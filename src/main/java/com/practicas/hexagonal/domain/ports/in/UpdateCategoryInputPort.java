package com.practicas.hexagonal.domain.ports.in;

import com.practicas.hexagonal.domain.model.Category;

public interface UpdateCategoryInputPort {
    Category update(Long id, Category category);
}
