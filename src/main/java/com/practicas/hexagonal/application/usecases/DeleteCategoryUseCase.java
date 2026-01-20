package com.practicas.hexagonal.application.usecases;

import com.practicas.hexagonal.domain.exception.CategoryNotFoundException;
import com.practicas.hexagonal.domain.ports.in.DeleteCategoryInputPort;
import com.practicas.hexagonal.domain.ports.out.CategoryRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteCategoryUseCase implements DeleteCategoryInputPort {
    private final CategoryRepositoryPort categoryRepository;

    @Override
    public void delete(Long id) {
        if (!categoryRepository.findById(id).isPresent()) {
            throw new CategoryNotFoundException(id);
        }

        categoryRepository.deleteById(id);
    }
}
