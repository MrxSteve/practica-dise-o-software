package com.practicas.hexagonal.application.usecases;

import com.practicas.hexagonal.domain.exception.DuplicateEntityException;
import com.practicas.hexagonal.domain.exception.EntityNotFoundException;
import com.practicas.hexagonal.domain.model.Category;
import com.practicas.hexagonal.domain.ports.in.UpdateCategoryInputPort;
import com.practicas.hexagonal.domain.ports.out.CategoryRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateCategoryUseCase implements UpdateCategoryInputPort {
    private final CategoryRepositoryPort categoryRepository;
    
    @Override
    public Category update(Long id, Category category) {
        Category existingCategory = categoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Category", id));

        category.validate();

        if (categoryRepository.existsByNameAndIdNot(category.getName(), id)) {
            throw new DuplicateEntityException("Category", "name", category.getName());
        }
        
        existingCategory.setName(category.getName());
        
        return categoryRepository.save(existingCategory);
    }
}
