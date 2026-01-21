package com.practicas.hexagonal.infrastructure.adapters.in.rest.controller;

import com.practicas.hexagonal.domain.model.Brand;
import com.practicas.hexagonal.domain.ports.in.CreateBrandInputPort;
import com.practicas.hexagonal.domain.ports.in.DeleteBrandInputPort;
import com.practicas.hexagonal.domain.ports.in.GetBrandInputPort;
import com.practicas.hexagonal.domain.ports.in.UpdateBrandInputPort;
import com.practicas.hexagonal.infrastructure.adapters.in.rest.dto.request.CreateBrandRequest;
import com.practicas.hexagonal.infrastructure.adapters.in.rest.dto.request.UpdateBrandRequest;
import com.practicas.hexagonal.infrastructure.adapters.in.rest.dto.response.BrandResponse;
import com.practicas.hexagonal.infrastructure.adapters.in.rest.mapper.BrandRestMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/brands")
@RequiredArgsConstructor
@Tag(name = "Brands", description = "API for managing brands")
public class BrandController {
    private final CreateBrandInputPort createBrandUseCase;
    private final UpdateBrandInputPort updateBrandUseCase;
    private final GetBrandInputPort getBrandUseCase;
    private final DeleteBrandInputPort deleteBrandUseCase;

    private final BrandRestMapper mapper;

    @PostMapping
    @Operation(summary = "Create a new brand")
    public ResponseEntity<BrandResponse> create(@Valid @RequestBody CreateBrandRequest request) {
        Brand brand = mapper.toDomain(request);

        Brand createdBrand = createBrandUseCase.create(brand);
        BrandResponse response = mapper.toResponse(createdBrand);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an existing brand")
    public ResponseEntity<BrandResponse> update(
            @PathVariable Long id,
            @Valid @RequestBody UpdateBrandRequest request) {
        Brand brand = mapper.toDomain(request);
        Brand updatedBrand = updateBrandUseCase.update(id, brand);
        BrandResponse response = mapper.toResponse(updatedBrand);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a brand")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        deleteBrandUseCase.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a brand by ID")
    public ResponseEntity<BrandResponse> getById(@PathVariable Long id) {
        Brand brand = getBrandUseCase.getById(id);
        BrandResponse response = mapper.toResponse(brand);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/name")
    @Operation(summary = "Get a brand by name")
    public ResponseEntity<BrandResponse> getByName(@RequestParam String name) {
        Brand brand = getBrandUseCase.getByName(name);
        BrandResponse response = mapper.toResponse(brand);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/paginated")
    @Operation(summary = "Get all brands with pagination")
    public ResponseEntity<List<BrandResponse>> getAllPaginated(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        List<Brand> brands = getBrandUseCase.getAll();
        List<BrandResponse> responses = brands
                .stream()
                .map(mapper::toResponse)
                .toList();

        return ResponseEntity.ok(responses);
    }

    @GetMapping
    @Operation(summary = "Get all brands")
    public ResponseEntity<List<BrandResponse>> getAll() {
        List<Brand> brands = getBrandUseCase.getAll();
        List<BrandResponse> responses = brands
                .stream()
                .map(mapper::toResponse)
                .toList();

        return ResponseEntity.ok(responses);
    }
}
