package com.practicas.hexagonal.domain.ports.in;

import com.practicas.hexagonal.domain.model.Category;

public interface CreateCategoryInputPort {
    Category create(Category category);
}
