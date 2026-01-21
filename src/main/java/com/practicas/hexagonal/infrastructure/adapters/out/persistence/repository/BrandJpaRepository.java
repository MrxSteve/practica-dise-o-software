package com.practicas.hexagonal.infrastructure.adapters.out.persistence.repository;

import com.practicas.hexagonal.infrastructure.adapters.out.persistence.entity.BrandEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface BrandJpaRepository extends JpaRepository<BrandEntity, Long> {
    boolean existsByName(String name);
    boolean existsByNameAndIdNot(String name, Long id);

    @Query("select b from BrandEntity b where lower(b.name) = lower(?1)")
    Optional<BrandEntity> findByName(String name);
}
