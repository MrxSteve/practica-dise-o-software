package com.practicas.hexagonal.application.usecases;

import com.practicas.hexagonal.domain.exception.DuplicateEntityException;
import com.practicas.hexagonal.domain.model.Category;
import com.practicas.hexagonal.domain.ports.in.CreateCategoryInputPort;
import com.practicas.hexagonal.domain.ports.out.CategoryRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateCategoryUseCase implements CreateCategoryInputPort {
    private final CategoryRepositoryPort categoryRepository;
    
    @Override
    public Category create(Category category) {
        category.validate();

        if (categoryRepository.existsByName(category.getName())) {
            throw new DuplicateEntityException("Category", "name", category.getName());
        }

        return categoryRepository.save(category);
    }
}
