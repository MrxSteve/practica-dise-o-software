package com.practicas.hexagonal.application.usecases;

import com.practicas.hexagonal.domain.exception.EntityNotFoundException;
import com.practicas.hexagonal.domain.model.Brand;
import com.practicas.hexagonal.domain.ports.in.GetBrandInputPort;
import com.practicas.hexagonal.domain.ports.out.BrandRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetBrandUseCase implements GetBrandInputPort {
    private final BrandRepositoryPort brandRepository;

    @Override
    public Brand getById(Long id) {
        return brandRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Brand", id));
    }

    @Override
    public List<Brand> getAll() {
        return brandRepository.findAll();
    }

    @Override
    public List<Brand> getAllPaginated(int page, int size) {
        return brandRepository.findAll(page, size);
    }

    @Override
    public Brand getByName(String name) {
        return brandRepository.findByName(name)
                .orElse(null);
    }
}
