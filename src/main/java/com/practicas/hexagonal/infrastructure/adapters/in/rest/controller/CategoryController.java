package com.practicas.hexagonal.infrastructure.adapters.in.rest.controller;

import com.practicas.hexagonal.domain.model.Category;
import com.practicas.hexagonal.domain.ports.in.CreateCategoryInputPort;
import com.practicas.hexagonal.domain.ports.in.DeleteCategoryInputPort;
import com.practicas.hexagonal.domain.ports.in.GetCategoryInputPort;
import com.practicas.hexagonal.domain.ports.in.UpdateCategoryInputPort;
import com.practicas.hexagonal.infrastructure.adapters.in.rest.dto.response.CategoryResponse;
import com.practicas.hexagonal.infrastructure.adapters.in.rest.dto.request.CreateCategoryRequest;
import com.practicas.hexagonal.infrastructure.adapters.in.rest.dto.request.UpdateCategoryRequest;
import com.practicas.hexagonal.infrastructure.adapters.in.rest.mapper.CategoryRestMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
@Tag(name = "Categories", description = "API for managing categories")
public class CategoryController {
    private final CreateCategoryInputPort createCategoryUseCase;
    private final UpdateCategoryInputPort updateCategoryUseCase;
    private final DeleteCategoryInputPort deleteCategoryUseCase;
    private final GetCategoryInputPort getCategoryUseCase;

    private final CategoryRestMapper mapper;
    
    @PostMapping
    @Operation(summary = "Create a new category")
    public ResponseEntity<CategoryResponse> create(@Valid @RequestBody CreateCategoryRequest request) {
        Category category = mapper.toDomain(request);

        Category createdCategory = createCategoryUseCase.create(category);
        CategoryResponse response = mapper.toResponse(createdCategory);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    
    @PutMapping("/{id}")
    @Operation(summary = "Update an existing category")
    public ResponseEntity<CategoryResponse> update(
            @PathVariable Long id,
            @Valid @RequestBody UpdateCategoryRequest request) {
        
        Category category = mapper.toDomain(request);
        Category updatedCategory = updateCategoryUseCase.update(id, category);
        CategoryResponse response = mapper.toResponse(updatedCategory);
        
        return ResponseEntity.ok(response);
    }
    
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a category")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        deleteCategoryUseCase.delete(id);
        return ResponseEntity.noContent().build();
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "Get a category by ID")
    public ResponseEntity<CategoryResponse> getById(@PathVariable Long id) {
        Category category = getCategoryUseCase.getById(id);
        CategoryResponse response = mapper.toResponse(category);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/name")
    @Operation(summary = "Get a category by name")
    public ResponseEntity<CategoryResponse> getByName(@RequestParam String name) {
        Category category = getCategoryUseCase.getByName(name);
        CategoryResponse response = mapper.toResponse(category);
        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/paginated")
    @Operation(summary = "Get all categories paginated",
            description = "Returns a paginated list of categories. " +
                    "Use query params: ?page=0&size=10")
    public ResponseEntity<List<CategoryResponse>> getAllPaginated(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        
        List<Category> categories = getCategoryUseCase.getAllPaginated(page, size);
        List<CategoryResponse> response = categories.stream()
                .map(mapper::toResponse)
                .toList();
        
        return ResponseEntity.ok(response);
    }
    
    @GetMapping
    @Operation(summary = "Get all categories")
    public ResponseEntity<List<CategoryResponse>> getAll() {
        List<Category> categories = getCategoryUseCase.getAll();
        List<CategoryResponse> response = mapper.toResponseList(categories);
        return ResponseEntity.ok(response);
    }
}
