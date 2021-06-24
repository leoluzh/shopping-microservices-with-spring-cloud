package com.lambdasys.microservices.inventoryserver.mapper;

import com.lambdasys.microservices.inventoryserver.dto.InventoryDto;
import com.lambdasys.microservices.inventoryserver.model.Inventory;
import org.mapstruct.MapperConfig;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@MapperConfig(componentModel = "spring")
public interface InventoryMapper {

    InventoryMapper INSTANCE = Mappers.getMapper(InventoryMapper.class);

    InventoryDto toDto(final Inventory inventory);

    Inventory toEntity(final InventoryDto inventoryDto);

    void update(final InventoryDto inventoryDto, @MappingTarget final Inventory inventory);

}
