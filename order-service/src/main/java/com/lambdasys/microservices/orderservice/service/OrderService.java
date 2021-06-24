package com.lambdasys.microservices.orderservice.service;

import com.lambdasys.microservices.orderservice.mapper.OrderItensMapper;
import com.lambdasys.microservices.orderservice.mapper.OrderMapper;
import com.lambdasys.microservices.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class OrderService implements Serializable {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final OrderItensMapper orderItensMapper;

}
