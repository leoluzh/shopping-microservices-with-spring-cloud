package com.lambdasys.microservices.inventoryservice.dto;

import lombok.*;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InventoryDto implements Serializable {

    private Long id;
    private String sku;
    private Integer stock;

}
