package com.lambdasys.microservices.productserver.controller;

import com.lambdasys.microservices.productserver.dto.ProductDto;
import com.lambdasys.microservices.productserver.exception.ProductNotFoundException;
import com.lambdasys.microservices.productserver.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public ResponseEntity<List<ProductDto>> findAll() {
        return ResponseEntity.ok(productService.findAll());
    }

    @GetMapping
    public ResponseEntity<Page<ProductDto>> findAll(final Pageable pageable) {
        return ResponseEntity.ok(productService.findAll(pageable));
    }

    @PostMapping
    public ResponseEntity<ProductDto> create(@RequestBody @Valid final ProductDto productDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.create(productDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDto> update(@PathVariable("id") final String id, @RequestBody @Valid final ProductDto productDto) throws ProductNotFoundException {
        return ResponseEntity.status(HttpStatus.OK).body(productService.update(id, productDto));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") String id) throws ProductNotFoundException {
        productService.delete(id);
    }

}
