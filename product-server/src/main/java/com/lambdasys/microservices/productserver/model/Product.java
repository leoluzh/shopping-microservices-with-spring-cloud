package com.lambdasys.microservices.productserver.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data

@Document("product")
public class Product implements Serializable {
    @Id
    private String id;
    private String name;
    private String description;
    private BigDecimal price;

}
