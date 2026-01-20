package com.practicas.hexagonal.infrastructure.adapters.out.persistence.adapter;

import com.practicas.hexagonal.domain.model.Category;
import com.practicas.hexagonal.domain.ports.out.CategoryRepositoryPort;
import com.practicas.hexagonal.infrastructure.adapters.out.persistence.entity.CategoryEntity;
import com.practicas.hexagonal.infrastructure.adapters.out.persistence.mapper.CategoryPersistenceMapper;
import com.practicas.hexagonal.infrastructure.adapters.out.persistence.repository.CategoryJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CategoryJpaAdapter implements CategoryRepositoryPort {
    private final CategoryJpaRepository jpaRepository;
    private final CategoryPersistenceMapper mapper;
    
    @Override
    public Category save(Category category) {
        CategoryEntity entity = mapper.toEntity(category);

        CategoryEntity savedEntity = jpaRepository.save(entity);

        return mapper.toDomain(savedEntity);
    }
    
    @Override
    public Optional<Category> findById(Long id) {
        return jpaRepository.findById(id)
                .map(mapper::toDomain);
    }

    @Override
    public Optional<Category> findByName(String name) {
        return jpaRepository.findByName(name)
                .map(mapper::toDomain);
    }

    @Override
    public List<Category> findAll() {
        List<CategoryEntity> entities = jpaRepository.findAll();
        return mapper.toDomainList(entities);
    }
    
    @Override
    public List<Category> findAll(int page, int size) {
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
