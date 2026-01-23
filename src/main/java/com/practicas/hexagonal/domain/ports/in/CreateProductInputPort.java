package com.practicas.hexagonal.domain.ports.in;

import com.practicas.hexagonal.domain.model.Product;

public interface CreateProductInputPort {
    Product create(Product product);
}
