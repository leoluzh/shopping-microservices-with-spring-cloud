package com.lambdasys.microservices.orderservice.controller;

import com.lambdasys.microservices.orderservice.client.InventoryClient;
import com.lambdasys.microservices.orderservice.dto.OrderDto;
import com.lambdasys.microservices.orderservice.exception.OrderFailureException;
import com.lambdasys.microservices.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JCircuitBreaker;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JCircuitBreakerFactory;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.io.Serializable;
import java.util.concurrent.ExecutorService;
import java.util.function.Supplier;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class OrderController implements Serializable {

    private final OrderService orderService;
    private final InventoryClient inventoryClient;
    private final Resilience4JCircuitBreakerFactory circuitBreakerFactory;
    private final StreamBridge streamBridge;
    private final ExecutorService traceableExecutorService;

    @PostMapping
    public ResponseEntity<OrderDto> create(@RequestBody @Valid OrderDto orderDto) throws OrderFailureException {

        final var circuitBreaker = createCircuitBreaker();

        final Supplier<Boolean> booleanSupplier = () -> orderDto
                .getOrderItens()
                .stream()
                .allMatch(item -> {
                    log.info("Making call to Inventory service for sku {}", item.getSku());
                    return inventoryClient.isInStock(item.getSku());
                });

        final var productsInStock = circuitBreaker.run(booleanSupplier, throwable -> handleErrorCase());

        if (productsInStock) {
            final var result = this.orderService.create(orderDto);
            log.info("Sending order details with order id {} to notification service",orderDto.getId());
            streamBridge.send("notificationEventSupplier-out-0", MessageBuilder.withPayload(orderDto.getId()).build());
            return ResponseEntity.ok(result);
        } else {
            throw new OrderFailureException("Order Failed - One of the item in your order is out of stock");
        }

    }

    private Resilience4JCircuitBreaker createCircuitBreaker() {
        this.circuitBreakerFactory.configureExecutorService(this.traceableExecutorService);
        return this.circuitBreakerFactory.create("inventory");
    }

    private boolean handleErrorCase() {
        return false;
    }

}
