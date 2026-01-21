package com.practicas.hexagonal.domain.ports.in;

import com.practicas.hexagonal.domain.model.Brand;

public interface UpdateBrandInputPort {
    Brand update(Long id, Brand brand);
}
