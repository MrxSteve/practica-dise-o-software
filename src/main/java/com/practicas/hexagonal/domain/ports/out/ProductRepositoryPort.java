package com.practicas.hexagonal.domain.ports.out;

import com.practicas.hexagonal.domain.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepositoryPort {
    Product save(Product product);
    Optional<Product> findById(Long id);
    Optional<Product> findBySku(String sku);
    List<Product> findAll();
    List<Product> findAll(int page, int size);
    List<Product> findByCategoryId(Long categoryId, int page, int size);
    List<Product> findByBrandId(Long brandId, int page, int size);
    List<Product> findByName(String name, int page, int size);
    void deleteById(Long id);
    boolean existsBySku(String sku);
    boolean existsBySkuAndIdNot(String sku, Long excludeId);
}
