package com.lambdasys.microservices.orderservice.dto;

import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto implements Serializable {

    private Long id;
    private String orderNumber;
    @Builder.Default
    private List<OrderItemDto> orderItens = new ArrayList<>();

}
