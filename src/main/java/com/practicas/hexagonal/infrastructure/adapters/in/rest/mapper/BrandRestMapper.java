package com.practicas.hexagonal.infrastructure.adapters.in.rest.mapper;

import com.practicas.hexagonal.domain.model.Brand;
import com.practicas.hexagonal.infrastructure.adapters.in.rest.dto.request.CreateBrandRequest;
import com.practicas.hexagonal.infrastructure.adapters.in.rest.dto.request.UpdateBrandRequest;
import com.practicas.hexagonal.infrastructure.adapters.in.rest.dto.response.BrandResponse;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE
)
public interface BrandRestMapper {
    Brand toDomain(CreateBrandRequest request);
    Brand toDomain(UpdateBrandRequest request);

    BrandResponse toResponse(Brand brand);
}
