package com.lambdasys.microservices.orderservice.repository;

import com.lambdasys.microservices.orderservice.model.Order;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends PagingAndSortingRepository<Order,Long> {
}
