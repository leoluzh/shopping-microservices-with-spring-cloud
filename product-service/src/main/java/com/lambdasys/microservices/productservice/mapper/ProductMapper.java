package com.lambdasys.microservices.productservice.mapper;

import com.lambdasys.microservices.productservice.dto.ProductDto;
import com.lambdasys.microservices.productservice.model.Product;
import org.mapstruct.MapperConfig;
import org.mapstruct.MappingTarget;

@MapperConfig(componentModel = "spring")
public interface ProductMapper {

    ProductDto toDto(final Product product);

    Product toEntity(final ProductDto productDto);

    // TODO: Configurar para nao mapear valores nulos.
    void update(final ProductDto productDto, @MappingTarget final Product product);

}
