package com.practicas.hexagonal.domain.ports.in;

import com.practicas.hexagonal.domain.model.Brand;

import java.util.List;

public interface GetBrandInputPort {
    Brand getById(Long id);
    List<Brand> getAll();
    List<Brand> getAllPaginated(int page, int size);
    Brand getByName(String name);
}
