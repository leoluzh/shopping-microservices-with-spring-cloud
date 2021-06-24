package com.lambdasys.microservices.inventoryservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class InventoryNotFoundException extends Exception {

    public InventoryNotFoundException(Long id){
        super(String.format("Inventory with id %s not found.",id));
    }

    public InventoryNotFoundException(String sku){
        super(String.format("Inventory with sku %s not found.",sku));
    }

}
