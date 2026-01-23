package com.practicas.hexagonal.infrastructure.adapters.in.rest.mapper;

import com.practicas.hexagonal.domain.model.Product;
import com.practicas.hexagonal.domain.ports.out.BrandRepositoryPort;
import com.practicas.hexagonal.domain.ports.out.CategoryRepositoryPort;
import com.practicas.hexagonal.infrastructure.adapters.in.rest.dto.request.CreateProductRequest;
import com.practicas.hexagonal.infrastructure.adapters.in.rest.dto.request.UpdateProductRequest;
import com.practicas.hexagonal.infrastructure.adapters.in.rest.dto.response.ProductResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE
)
public abstract class ProductRestMapper {
    @Autowired
    protected CategoryRepositoryPort categoryRepository;
    
    @Autowired
    protected BrandRepositoryPort brandRepository;

    public abstract Product toDomain(CreateProductRequest request);
    public abstract Product toDomain(UpdateProductRequest request);

    @Mapping(target = "categoryName", expression = "java(getCategoryName(product.getCategoryId()))")
    @Mapping(target = "brandName", expression = "java(getBrandName(product.getBrandId()))")
    public abstract ProductResponse toResponse(Product product);

    public abstract List<ProductResponse> toResponseList(List<Product> products);

    // Metodos helper para obtener los nombres
    protected String getCategoryName(Long categoryId) {
        if (categoryId == null) return null;
        return categoryRepository.findById(categoryId)
                .map(category -> category.getName())
                .orElse("Unknown");
    }

    protected String getBrandName(Long brandId) {
        if (brandId == null) return null;
        return brandRepository.findById(brandId)
                .map(brand -> brand.getName())
                .orElse("Unknown");
    }
}
