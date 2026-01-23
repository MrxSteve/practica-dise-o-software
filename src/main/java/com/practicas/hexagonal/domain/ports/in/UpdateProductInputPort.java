package com.practicas.hexagonal.domain.ports.in;

import com.practicas.hexagonal.domain.model.Product;

public interface UpdateProductInputPort {
    Product update(Long id, Product product);
    void addStock(Long id, int quantity);
    void removeStock(Long id, int quantity);
}
