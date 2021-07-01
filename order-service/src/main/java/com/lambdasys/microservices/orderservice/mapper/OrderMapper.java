package com.lambdasys.microservices.orderservice.mapper;

import com.lambdasys.microservices.orderservice.dto.OrderDto;
import com.lambdasys.microservices.orderservice.model.Order;
import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.MapperConfig;

@MapperConfig(componentModel = "spring", uses = {
        OrderItemMapper.class
}, collectionMappingStrategy = CollectionMappingStrategy.ACCESSOR_ONLY
)
public interface OrderMapper {

    Order toEntity(OrderDto orderDto);

    OrderDto toDto(Order order);

}
