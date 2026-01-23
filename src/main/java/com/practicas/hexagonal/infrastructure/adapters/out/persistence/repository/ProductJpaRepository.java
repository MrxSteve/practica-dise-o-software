package com.practicas.hexagonal.infrastructure.adapters.out.persistence.repository;

import com.practicas.hexagonal.infrastructure.adapters.out.persistence.entity.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface ProductJpaRepository extends JpaRepository<ProductEntity, Long> {
    Optional<ProductEntity> findBySku(String sku);
    List<ProductEntity> findByCategoryId(Long categoryId, Pageable pageable);
    List<ProductEntity> findByBrandId(Long brandId, Pageable pageable);
    @Query("select p from ProductEntity p where lower(p.name) like lower(concat('%', ?1, '%'))")
    List<ProductEntity> findByName(String name, Pageable pageable);
    boolean existsBySku(String sku);
    boolean existsBySkuAndIdNot(String sku, Long excludeId);
    Page<ProductEntity> findAll(Pageable pageable);
}
