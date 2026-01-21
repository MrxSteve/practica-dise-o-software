package com.practicas.hexagonal.domain.ports.in;

import com.practicas.hexagonal.domain.model.Brand;

public interface CreateBrandInputPort {
    Brand create(Brand brand);
}
