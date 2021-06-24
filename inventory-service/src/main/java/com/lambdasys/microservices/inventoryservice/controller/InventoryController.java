package com.lambdasys.microservices.inventoryservice.controller;

import com.lambdasys.microservices.inventoryservice.dto.InventoryDto;
import com.lambdasys.microservices.inventoryservice.exception.InventoryNotFoundException;
import com.lambdasys.microservices.inventoryservice.service.InventoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.Serializable;
import java.util.List;

@RestController
@RequestMapping("/api/v1/inventories")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class InventoryController implements Serializable {

    private final InventoryService inventoryService;

    @GetMapping
    public ResponseEntity<List<InventoryDto>> findAll(){
        log.info("Finding all inventory");
        return ResponseEntity.ok(this.inventoryService.findAll());
    }

    @GetMapping
    public Page<InventoryDto> findAll(final Pageable pageable){
        log.info("Finding all inventory by pageable {}",pageable);
        return this.inventoryService.findAll(pageable);
    }

    @PostMapping
    public ResponseEntity<InventoryDto> create(@RequestBody @Valid final InventoryDto inventoryDto){
        log.info("Creating an new inventory {}",inventoryDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(this.inventoryService.create(inventoryDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<InventoryDto> update(@PathVariable("id") final Long id, @RequestBody @Valid InventoryDto inventoryDto) throws InventoryNotFoundException {
        log.info("Updating an inventory with id {} and values {}",id,inventoryDto);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(this.inventoryService.update(id,inventoryDto));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") final Long id) throws InventoryNotFoundException {
        log.info("Deleting an inventory with id {}",id);
        this.inventoryService.delete(id);
    }

    @GetMapping("/in-stock/{sku}")
    public ResponseEntity<Boolean> isInStock(@PathVariable("sku") final String sku) throws InventoryNotFoundException {
        log.info("Checking stock for product with sku {}",sku);
        final var inventory = this.inventoryService.findBySku(sku);
        return ResponseEntity.status(HttpStatus.OK).body(inventory.getStock()>0);
    }

}
