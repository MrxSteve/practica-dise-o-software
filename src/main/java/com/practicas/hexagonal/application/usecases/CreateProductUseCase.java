package com.practicas.hexagonal.application.usecases;

import com.practicas.hexagonal.domain.exception.DuplicateEntityException;
import com.practicas.hexagonal.domain.exception.EntityNotFoundException;
import com.practicas.hexagonal.domain.model.Product;
import com.practicas.hexagonal.domain.ports.in.CreateProductInputPort;
import com.practicas.hexagonal.domain.ports.out.BrandRepositoryPort;
import com.practicas.hexagonal.domain.ports.out.CategoryRepositoryPort;
import com.practicas.hexagonal.domain.ports.out.ProductRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateProductUseCase implements CreateProductInputPort {
    private final ProductRepositoryPort productRepository;
    private final CategoryRepositoryPort categoryRepository;
    private final BrandRepositoryPort brandRepository;
    
    @Override
    public Product create(Product product) {
        product.validate();

        if (productRepository.existsBySku(product.getSku())) {
            throw new DuplicateEntityException("Product", "sku", product.getSku());
        }

        if (!categoryRepository.findById(product.getCategoryId()).isPresent()) {
            throw new EntityNotFoundException("Category", product.getCategoryId());
        }

        if (!brandRepository.findById(product.getBrandId()).isPresent()) {
            throw new EntityNotFoundException("Brand", product.getBrandId());
        }

        return productRepository.save(product);
    }
}
