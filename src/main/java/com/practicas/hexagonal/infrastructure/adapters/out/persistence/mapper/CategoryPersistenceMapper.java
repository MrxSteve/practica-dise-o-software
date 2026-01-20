package com.practicas.hexagonal.infrastructure.adapters.out.persistence.mapper;

import com.practicas.hexagonal.domain.model.Category;
import com.practicas.hexagonal.infrastructure.adapters.out.persistence.entity.CategoryEntity;
import org.mapstruct.Mapper;
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
public interface CategoryPersistenceMapper {
    Category toDomain(CategoryEntity entity);
    CategoryEntity toEntity(Category domain);

    List<Category> toDomainList(List<CategoryEntity> entities);

    void updateEntity(@MappingTarget CategoryEntity entity, Category domain);
}
