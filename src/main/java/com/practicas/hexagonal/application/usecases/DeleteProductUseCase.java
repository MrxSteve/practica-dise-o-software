package com.practicas.hexagonal.application.usecases;

import com.practicas.hexagonal.domain.exception.EntityNotFoundException;
import com.practicas.hexagonal.domain.ports.in.DeleteProductInputPort;
import com.practicas.hexagonal.domain.ports.out.ProductRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteProductUseCase implements DeleteProductInputPort {
    private final ProductRepositoryPort productRepository;
    
    @Override
    public void delete(Long id) {
        if (!productRepository.findById(id).isPresent()) {
            throw new EntityNotFoundException("Product", id);
        }
        
        productRepository.deleteById(id);
    }
}
