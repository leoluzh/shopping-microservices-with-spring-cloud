package com.lambdasys.microservices.orderservice.service;

import com.lambdasys.microservices.orderservice.dto.OrderDto;
import com.lambdasys.microservices.orderservice.mapper.OrderItemMapper;
import com.lambdasys.microservices.orderservice.mapper.OrderMapper;
import com.lambdasys.microservices.orderservice.model.Order;
import com.lambdasys.microservices.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.UUID;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class OrderService implements Serializable {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final OrderItemMapper orderItensMapper;

    public OrderDto create(final OrderDto orderDto){
       var order = this.orderMapper.toEntity(orderDto);
        order.setOrderNumber(UUID.randomUUID().toString());
        this.orderRepository.save(order);
        return this.orderMapper.toDto(order);
    }

}
