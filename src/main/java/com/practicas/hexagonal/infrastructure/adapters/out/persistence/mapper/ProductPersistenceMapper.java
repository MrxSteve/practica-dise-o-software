package com.practicas.hexagonal.infrastructure.adapters.out.persistence.mapper;

import com.practicas.hexagonal.domain.model.Product;
import com.practicas.hexagonal.infrastructure.adapters.out.persistence.entity.ProductEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE
)
public interface ProductPersistenceMapper {
    @Mapping(target = "categoryId", source = "category.id")
    @Mapping(target = "brandId", source = "brand.id")
    Product toDomain(ProductEntity entity);

    @Mapping(target = "category", ignore = true)
    @Mapping(target = "brand", ignore = true)
    ProductEntity toEntity(Product domain);

    List<Product> toDomainList(List<ProductEntity> entities);

    @Mapping(target = "category", ignore = true)
    @Mapping(target = "brand", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    void updateEntity(@MappingTarget ProductEntity entity, Product domain);
}
