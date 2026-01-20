package com.practicas.hexagonal.domain.ports.out;

import com.practicas.hexagonal.domain.model.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryRepositoryPort {
    Category save(Category category);
    Optional<Category> findById(Long id);
    Optional<Category> findByName(String name);
    List<Category> findAll();
    List<Category> findAll(int page, int size);
    void deleteById(Long id);
    boolean existsByName(String name);
    boolean existsByNameAndIdNot(String name, Long excludeId);
}
