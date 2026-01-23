package com.practicas.hexagonal.infrastructure.adapters.in.rest.controller;

import com.practicas.hexagonal.domain.model.Product;
import com.practicas.hexagonal.domain.ports.in.CreateProductInputPort;
import com.practicas.hexagonal.domain.ports.in.DeleteProductInputPort;
import com.practicas.hexagonal.domain.ports.in.GetProductInputPort;
import com.practicas.hexagonal.domain.ports.in.UpdateProductInputPort;
import com.practicas.hexagonal.infrastructure.adapters.in.rest.dto.request.CreateProductRequest;
import com.practicas.hexagonal.infrastructure.adapters.in.rest.dto.request.UpdateProductRequest;
import com.practicas.hexagonal.infrastructure.adapters.in.rest.dto.response.ProductResponse;
import com.practicas.hexagonal.infrastructure.adapters.in.rest.mapper.ProductRestMapper;
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
@RequestMapping("/api/products")
@RequiredArgsConstructor
@Tag(name = "Products", description = "API for managing products")
public class ProductController {
    private final CreateProductInputPort createProductUseCase;
    private final UpdateProductInputPort updateProductUseCase;
    private final DeleteProductInputPort deleteProductUseCase;
    private final GetProductInputPort getProductUseCase;

    private final ProductRestMapper mapper;
    
    @PostMapping
    @Operation(summary = "Create a new product")
    public ResponseEntity<ProductResponse> create(@Valid @RequestBody CreateProductRequest request) {
        Product product = mapper.toDomain(request);

        Product createdProduct = createProductUseCase.create(product);
        ProductResponse response = mapper.toResponse(createdProduct);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    
    @PutMapping("/{id}")
    @Operation(summary = "Update an existing product")
    public ResponseEntity<ProductResponse> update(
            @PathVariable Long id,
            @Valid @RequestBody UpdateProductRequest request) {
        Product product = mapper.toDomain(request);
        Product updatedProduct = updateProductUseCase.update(id, product);
        ProductResponse response = mapper.toResponse(updatedProduct);
        
        return ResponseEntity.ok(response);
    }
    
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a product")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        deleteProductUseCase.delete(id);
        return ResponseEntity.noContent().build();
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "Get a product by ID")
    public ResponseEntity<ProductResponse> getById(@PathVariable Long id) {
        Product product = getProductUseCase.getById(id);
        ProductResponse response = mapper.toResponse(product);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/paginated")
    @Operation(summary = "Get all products paginated",
            description = "Returns a paginated list of products. " +
                    "Use query params: ?page=0&size=10")
    public ResponseEntity<List<ProductResponse>> getAllPaginated(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size) {
        List<Product> products = getProductUseCase.getAll(page, size);
        List<ProductResponse> responses = mapper.toResponseList(products);

        return ResponseEntity.ok(responses);
    }

    @GetMapping
    @Operation(summary = "Get all products")
    public ResponseEntity<List<ProductResponse>> getAll() {
        List<Product> products = getProductUseCase.getAll();
        List<ProductResponse> responses = mapper.toResponseList(products);

        return ResponseEntity.ok(responses);
    }

    @GetMapping("/sku/{sku}")
    @Operation(summary = "Get a product by SKU")
    public ResponseEntity<ProductResponse> getBySku(@PathVariable String sku) {
        Product product = getProductUseCase.getBySku(sku);
        ProductResponse response = mapper.toResponse(product);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/name")
    @Operation(summary = "Get products by name",
            description = "Returns a paginated list of products filtered by name. " +
                    "Use query params: ?name=example&page=0&size=10")
    public ResponseEntity<List<ProductResponse>> getByName(
            @RequestParam String name,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size) {
        List<Product> products = getProductUseCase.getByName(name, page, size);
        List<ProductResponse> responses = mapper.toResponseList(products);

        return ResponseEntity.ok(responses);
    }

    @GetMapping("/category")
    @Operation(summary = "Get products by category ID",
            description = "Returns a paginated list of products filtered by category ID. " +
                    "Use query params: ?categoryId=1&page=0&size=10")
    public ResponseEntity<List<ProductResponse>> getByCategoryId(
            @RequestParam Long categoryId,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size) {
        List<Product> products = getProductUseCase.getByCategoryId(categoryId, page, size);
        List<ProductResponse> responses = mapper.toResponseList(products);

        return ResponseEntity.ok(responses);
    }

    @GetMapping("/brand")
    @Operation(summary = "Get products by brand ID",
            description = "Returns a paginated list of products filtered by brand ID. " +
                    "Use query params: ?brandId=1&page=0&size=10")
    public ResponseEntity<List<ProductResponse>> getByBrandId(
            @RequestParam Long brandId,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size) {
        List<Product> products = getProductUseCase.getByBrandId(brandId, page, size);
        List<ProductResponse> responses = mapper.toResponseList(products);

        return ResponseEntity.ok(responses);
    }
}