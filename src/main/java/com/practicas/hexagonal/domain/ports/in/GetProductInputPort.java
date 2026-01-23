package com.practicas.hexagonal.domain.ports.in;

import com.practicas.hexagonal.domain.model.Product;

import java.util.List;

public interface GetProductInputPort {
    Product getById(Long id);
    List<Product> getAll();
    List<Product> getAll(int page, int size);
    Product getBySku(String sku);
    List<Product> getByCategoryId(Long categoryId, int page, int size);
    List<Product> getByBrandId(Long brandId, int page, int size);
    List<Product> getByName(String name, int page, int size);
}
