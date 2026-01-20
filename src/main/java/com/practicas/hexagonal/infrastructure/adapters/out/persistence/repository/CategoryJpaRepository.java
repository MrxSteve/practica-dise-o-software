package com.practicas.hexagonal.infrastructure.adapters.out.persistence.repository;

import com.practicas.hexagonal.infrastructure.adapters.out.persistence.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CategoryJpaRepository extends JpaRepository<CategoryEntity, Long> {
    boolean existsByName(String name);
    boolean existsByNameAndIdNot(String name, Long id);

    @Query("select c from CategoryEntity c where lower(c.name) = lower(?1)")
    Optional<CategoryEntity> findByName(String name);
}
