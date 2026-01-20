package com.practicas.hexagonal.infrastructure.adapters.in.rest.mapper;

import com.practicas.hexagonal.domain.model.Category;
import com.practicas.hexagonal.infrastructure.adapters.in.rest.dto.response.CategoryResponse;
import com.practicas.hexagonal.infrastructure.adapters.in.rest.dto.request.CreateCategoryRequest;
import com.practicas.hexagonal.infrastructure.adapters.in.rest.dto.request.UpdateCategoryRequest;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE
)
public interface CategoryRestMapper {
    Category toDomain(CreateCategoryRequest request);
    Category toDomain(UpdateCategoryRequest request);

    CategoryResponse toResponse(Category category);

    List<CategoryResponse> toResponseList(List<Category> categories);
}
