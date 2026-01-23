package com.practicas.hexagonal.application.usecases;

import com.practicas.hexagonal.domain.exception.DuplicateEntityException;
import com.practicas.hexagonal.domain.exception.EntityNotFoundException;
import com.practicas.hexagonal.domain.exception.InvalidEntityException;
import com.practicas.hexagonal.domain.model.Product;
import com.practicas.hexagonal.domain.ports.in.UpdateProductInputPort;
import com.practicas.hexagonal.domain.ports.out.BrandRepositoryPort;
import com.practicas.hexagonal.domain.ports.out.CategoryRepositoryPort;
import com.practicas.hexagonal.domain.ports.out.ProductRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateProductUseCase implements UpdateProductInputPort {
    private final ProductRepositoryPort productRepository;
    private final CategoryRepositoryPort categoryRepository;
    private final BrandRepositoryPort brandRepository;
    
    @Override
    public Product update(Long id, Product product) {
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Product", id));

        if (product.getSku() != null && !product.getSku().equals(existingProduct.getSku())) {
            if (productRepository.existsBySkuAndIdNot(product.getSku(), id)) {
                throw new DuplicateEntityException("Product", "sku", product.getSku());
            }
        }
        
        if (product.getCategoryId() != null) {
            if (!categoryRepository.findById(product.getCategoryId()).isPresent()) {
                throw new EntityNotFoundException("Category", product.getCategoryId());
            }
        }
        
        if (product.getBrandId() != null) {
            if (!brandRepository.findById(product.getBrandId()).isPresent()) {
                throw new EntityNotFoundException("Brand", product.getBrandId());
            }
        }

        product.setId(id);

        return productRepository.save(product);
    }

    @Override
    public void addStock(Long id, int quantity) {
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Product", id));
        existingProduct.setStock(existingProduct.getStock() + quantity);

        productRepository.save(existingProduct);
    }

    @Override
    public void removeStock(Long id, int quantity) {
        if (quantity <= 0) {
            throw new InvalidEntityException("Quantity to remove must be greater than 0");
        }

        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Product", id));

        if (existingProduct.getStock() < quantity) {
            throw new InvalidEntityException(
                    String.format("Insufficient stock. Available: %d, Requested: %d",
                            existingProduct.getStock(), quantity)
            );
        }

        existingProduct.setStock(existingProduct.getStock() - quantity);
        productRepository.save(existingProduct);
    }
}
