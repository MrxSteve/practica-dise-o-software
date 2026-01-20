package com.practicas.hexagonal.application.usecases;

import com.practicas.hexagonal.domain.exception.CategoryNotFoundException;
import com.practicas.hexagonal.domain.model.Category;
import com.practicas.hexagonal.domain.ports.in.GetCategoryInputPort;
import com.practicas.hexagonal.domain.ports.out.CategoryRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetCategoryUseCase implements GetCategoryInputPort {
    private final CategoryRepositoryPort categoryRepository;
    
    @Override
    public Category getById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException(id));
    }
    
    @Override
    public List<Category> getAll() {
        return categoryRepository.findAll();
    }
    
    @Override
    public List<Category> getAllPaginated(int page, int size) {
        return categoryRepository.findAll(page, size);
    }

    @Override
    public Category getByName(String name) {
        return categoryRepository.findByName(name)
                .orElse(null);
    }
}
