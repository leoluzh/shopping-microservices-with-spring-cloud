package com.lambdasys.microservices.inventoryserver.dto;

import lombok.*;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
