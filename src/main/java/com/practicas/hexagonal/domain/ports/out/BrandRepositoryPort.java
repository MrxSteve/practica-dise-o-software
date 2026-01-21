package com.practicas.hexagonal.domain.ports.out;

import com.practicas.hexagonal.domain.model.Brand;

import java.util.List;
import java.util.Optional;

public interface BrandRepositoryPort {
    Brand save(Brand brand);
    Optional<Brand> findById(Long id);
    Optional<Brand> findByName(String name);
    List<Brand> findAll();
    List<Brand> findAll(int page, int size);
    void deleteById(Long id);
    boolean existsByName(String name);
    boolean existsByNameAndIdNot(String name, Long excludeId);
}
