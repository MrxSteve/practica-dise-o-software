package com.practicas.hexagonal.domain.ports.in;

import com.practicas.hexagonal.domain.model.Category;

import java.util.List;

public interface GetCategoryInputPort {
    Category getById(Long id);
    List<Category> getAll();
    List<Category> getAllPaginated(int page, int size);
    Category getByName(String name);
}
