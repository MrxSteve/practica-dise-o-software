package com.practicas.hexagonal.infrastructure.adapters.out.persistence.adapter;

import com.practicas.hexagonal.domain.model.Product;
import com.practicas.hexagonal.domain.ports.out.ProductRepositoryPort;
import com.practicas.hexagonal.infrastructure.adapters.out.persistence.entity.BrandEntity;
import com.practicas.hexagonal.infrastructure.adapters.out.persistence.entity.CategoryEntity;
import com.practicas.hexagonal.infrastructure.adapters.out.persistence.entity.ProductEntity;
import com.practicas.hexagonal.infrastructure.adapters.out.persistence.mapper.ProductPersistenceMapper;
import com.practicas.hexagonal.infrastructure.adapters.out.persistence.repository.BrandJpaRepository;
import com.practicas.hexagonal.infrastructure.adapters.out.persistence.repository.CategoryJpaRepository;
import com.practicas.hexagonal.infrastructure.adapters.out.persistence.repository.ProductJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ProductJpaAdapter implements ProductRepositoryPort {
    private final ProductJpaRepository productRepository;
    private final CategoryJpaRepository categoryRepository;
    private final BrandJpaRepository brandRepository;
    private final ProductPersistenceMapper mapper;

    @Override
    public Product save(Product product) {
        ProductEntity entity;

        if (product.getId() != null) {
            // UPDATE
            entity = productRepository.findById(product.getId())
                    .orElse(null);

            mapper.updateEntity(entity, product);

            if (product.getCategoryId() != null) {
                CategoryEntity category = categoryRepository.findById(product.getCategoryId())
                        .orElse(null);
                entity.setCategory(category);
            }
            
            if (product.getBrandId() != null) {
                BrandEntity brand = brandRepository.findById(product.getBrandId())
                        .orElse(null);
                entity.setBrand(brand);
            }
        } else {
            // CREATE
            entity = mapper.toEntity(product);

            CategoryEntity category = categoryRepository.findById(product.getCategoryId())
                    .orElse(null);
            BrandEntity brand = brandRepository.findById(product.getBrandId())
                            .orElse(null);
            
            entity.setCategory(category);
            entity.setBrand(brand);
        }
        
        ProductEntity savedEntity = productRepository.save(entity);
        return mapper.toDomain(savedEntity);
    }

    @Override
    public Optional<Product> findById(Long id) {
        return productRepository.findById(id)
                .map(mapper::toDomain);
    }

    @Override
    public Optional<Product> findBySku(String sku) {
        return productRepository.findBySku(sku)
                .map(mapper::toDomain);
    }

    @Override
    public List<Product> findAll() {
        return mapper.toDomainList(productRepository.findAll());
    }

    @Override
    public List<Product> findAll(int page, int size) {
        return productRepository.findAll(PageRequest.of(page, size))
                .map(mapper::toDomain)
                .getContent();
    }

    @Override
    public List<Product> findByCategoryId(Long categoryId, int page, int size) {
        return productRepository.findByCategoryId(categoryId, PageRequest.of(page, size))
                .stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public List<Product> findByBrandId(Long brandId, int page, int size) {
        return productRepository.findByBrandId(brandId, PageRequest.of(page, size))
                .stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public List<Product> findByName(String name, int page, int size) {
        return productRepository.findByName(name, PageRequest.of(page, size))
                .stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public boolean existsBySku(String sku) {
        return productRepository.existsBySku(sku);
    }

    @Override
    public boolean existsBySkuAndIdNot(String sku, Long excludeId) {
        return productRepository.existsBySkuAndIdNot(sku, excludeId);
    }
}
