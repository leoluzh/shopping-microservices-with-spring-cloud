package com.lambdasys.microservices.productservice.service;


import com.lambdasys.microservices.productservice.dto.ProductDto;
import com.lambdasys.microservices.productservice.exception.ProductNotFoundException;
import com.lambdasys.microservices.productservice.mapper.ProductMapper;
import com.lambdasys.microservices.productservice.model.Product;
import com.lambdasys.microservices.productservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public List<ProductDto> findAll(){
        return this.productRepository
                .findAll()
                .stream()
                .map(productMapper::toDto)
                .toList();
    }

    public Page<ProductDto> findAll(final Pageable pageable){
        return this.productRepository
                .findAll(pageable)
                .map(productMapper::toDto);
    }

    public List<ProductDto> findAll(final Sort sort){
        return this.productRepository
                .findAll(sort)
                .stream()
                .map(productMapper::toDto)
                .toList();
    }

    public ProductDto findById(final String id) throws ProductNotFoundException {
        var product = verififyIfExists(id);
        return productMapper.toDto(product);
    }

    public ProductDto create(final ProductDto productDto){
        final var product = productMapper.toEntity(productDto);
        final var result= this.productRepository.save(product);
        return productMapper.toDto(result);
    }

    public ProductDto update(final String id, final ProductDto productDto) throws ProductNotFoundException {
        final var product = verififyIfExists(id);
        productMapper.update(productDto,product);
        var result = productRepository.save(product);
        return productMapper.toDto(result);
    }

    public void delete(final String id) throws ProductNotFoundException {
        final var product = verififyIfExists(id);
        this.productRepository.delete(product);
    }

    public Product verififyIfExists(String id) throws ProductNotFoundException {
        return this.productRepository
                .findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
    }

}
