package com.practicas.hexagonal.application.usecases;

import com.practicas.hexagonal.domain.exception.EntityNotFoundException;
import com.practicas.hexagonal.domain.ports.in.DeleteBrandInputPort;
import com.practicas.hexagonal.domain.ports.out.BrandRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteBrandUseCase implements DeleteBrandInputPort {
    private final BrandRepositoryPort brandRepository;

    @Override
    public void delete(Long id) {
        if (!brandRepository.findById(id).isPresent()) {
            throw new EntityNotFoundException("Brand", id);
        }

        brandRepository.deleteById(id);
    }
}
