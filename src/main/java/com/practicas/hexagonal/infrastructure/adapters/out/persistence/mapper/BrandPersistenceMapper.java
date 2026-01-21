package com.practicas.hexagonal.infrastructure.adapters.out.persistence.mapper;

import com.practicas.hexagonal.domain.model.Brand;
import com.practicas.hexagonal.infrastructure.adapters.out.persistence.entity.BrandEntity;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE
)
public interface BrandPersistenceMapper {
    Brand toDomain(BrandEntity entity);
    BrandEntity toEntity(Brand domain);
}
