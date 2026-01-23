package com.practicas.hexagonal.application.usecases;

import com.practicas.hexagonal.domain.exception.EntityNotFoundException;
import com.practicas.hexagonal.domain.model.Product;
import com.practicas.hexagonal.domain.ports.in.GetProductInputPort;
import com.practicas.hexagonal.domain.ports.out.ProductRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetProductUseCase implements GetProductInputPort {
    private final ProductRepositoryPort productRepository;
    
    @Override
    public Product getById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Product", id));
    }

    @Override
    public List<Product> getAll() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> getAll(int page, int size) {
        return productRepository.findAll(page, size);
    }

    @Override
    public Product getBySku(String sku) {
        return productRepository.findBySku(sku)
                .orElseThrow(() -> new EntityNotFoundException("Product", "sku", sku));
    }

    @Override
    public List<Product> getByCategoryId(Long categoryId, int page, int size) {
        return productRepository.findByCategoryId(categoryId, page, size);
    }

    @Override
    public List<Product> getByBrandId(Long brandId, int page, int size) {
        return productRepository.findByBrandId(brandId, page, size);
    }

    @Override
    public List<Product> getByName(String name, int page, int size) {
        return productRepository.findByName(name, page, size);
    }
}
