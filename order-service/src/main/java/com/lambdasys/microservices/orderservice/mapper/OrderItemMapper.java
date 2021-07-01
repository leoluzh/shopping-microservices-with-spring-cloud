package com.lambdasys.microservices.orderservice.mapper;

import com.lambdasys.microservices.orderservice.dto.OrderItemDto;
import com.lambdasys.microservices.orderservice.model.OrderItem;
import org.mapstruct.MapperConfig;

@MapperConfig(componentModel = "spring")
public interface OrderItemMapper {

    OrderItem toEntity(OrderItemDto orderItemsDto);

    OrderItemDto toDto(OrderItem orderItems);

}
