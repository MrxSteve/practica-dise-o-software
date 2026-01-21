package com.practicas.hexagonal.application.usecases;

import com.practicas.hexagonal.domain.exception.DuplicateEntityException;
import com.practicas.hexagonal.domain.model.Brand;
import com.practicas.hexagonal.domain.ports.in.CreateBrandInputPort;
import com.practicas.hexagonal.domain.ports.out.BrandRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateBrandUseCase implements CreateBrandInputPort {
    private final BrandRepositoryPort brandRepository;

    @Override
    public Brand create(Brand brand) {
        brand.validate();

        if (brandRepository.existsByName(brand.getName())) {
            throw new DuplicateEntityException("Brand", "name", brand.getName());
        }

        return brandRepository.save(brand);
    }
}
