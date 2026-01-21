package com.practicas.hexagonal.infrastructure.adapters.out.persistence.adapter;

import com.practicas.hexagonal.domain.model.Brand;
import com.practicas.hexagonal.domain.ports.out.BrandRepositoryPort;
import com.practicas.hexagonal.infrastructure.adapters.out.persistence.entity.BrandEntity;
import com.practicas.hexagonal.infrastructure.adapters.out.persistence.mapper.BrandPersistenceMapper;
import com.practicas.hexagonal.infrastructure.adapters.out.persistence.repository.BrandJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class BrandJpaAdapter implements BrandRepositoryPort {
    private final BrandJpaRepository jpaRepository;
    private final BrandPersistenceMapper mapper;

    @Override
    public Brand save(Brand brand) {
        BrandEntity entity = mapper.toEntity(brand);
        BrandEntity savedEntity = jpaRepository.save(entity);

        return mapper.toDomain(savedEntity);
    }

    @Override
    public Optional<Brand> findById(Long id) {
        return jpaRepository.findById(id)
                .map(mapper::toDomain);
    }

    @Override
    public Optional<Brand> findByName(String name) {
        return jpaRepository.findByName(name)
                .map(mapper::toDomain);
    }

    @Override
    public List<Brand> findAll() {
        return jpaRepository.findAll()
                .stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public List<Brand> findAll(int page, int size) {
        return jpaRepository.findAll(PageRequest.of(page, size))
                .getContent()
                .stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public void deleteById(Long id) {
        jpaRepository.deleteById(id);
    }

    @Override
    public boolean existsByName(String name) {
        return jpaRepository.existsByName(name);
    }

    @Override
    public boolean existsByNameAndIdNot(String name, Long excludeId) {
        return jpaRepository.existsByNameAndIdNot(name, excludeId);
    }
}
