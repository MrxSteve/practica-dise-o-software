package com.practicas.hexagonal.application.usecases;

import com.practicas.hexagonal.domain.exception.DuplicateEntityException;
import com.practicas.hexagonal.domain.exception.EntityNotFoundException;
import com.practicas.hexagonal.domain.model.Brand;
import com.practicas.hexagonal.domain.ports.in.UpdateBrandInputPort;
import com.practicas.hexagonal.domain.ports.out.BrandRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateBrandUseCase implements UpdateBrandInputPort {
    private final BrandRepositoryPort brandRepository;

    @Override
    public Brand update(Long id, Brand brand) {
        Brand existingBrand = brandRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Brand", id));

        brand.validate();

        if (brandRepository.existsByNameAndIdNot(existingBrand.getName(),id)) {
            throw new DuplicateEntityException("Brand", "name", brand.getName());
        }

        existingBrand.setName(brand.getName());

        return brandRepository.save(existingBrand);
    }
}
