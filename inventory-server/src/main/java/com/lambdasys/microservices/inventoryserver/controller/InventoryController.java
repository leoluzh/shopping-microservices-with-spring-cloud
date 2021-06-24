package com.lambdasys.microservices.inventoryserver.controller;

import com.lambdasys.microservices.inventoryserver.dto.InventoryDto;
import com.lambdasys.microservices.inventoryserver.exception.InventoryNotFoundException;
import com.lambdasys.microservices.inventoryserver.service.InventoryService;
import lombok.RequiredArgsConstructor;
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
public class InventoryController implements Serializable {

    private final InventoryService inventoryService;

    @GetMapping
    public ResponseEntity<List<InventoryDto>> findAll(){
        return ResponseEntity.ok(this.inventoryService.findAll());
    }

    @GetMapping
    public Page<InventoryDto> findAll(final Pageable pageable){
        return this.inventoryService.findAll(pageable);
    }

    @PostMapping
    public ResponseEntity<InventoryDto> create(@RequestBody @Valid final InventoryDto inventoryDto){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(this.inventoryService.create(inventoryDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<InventoryDto> update(@PathVariable("id") final Long id, @RequestBody @Valid InventoryDto inventoryDto) throws InventoryNotFoundException {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(this.inventoryService.update(id,inventoryDto));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") final Long id) throws InventoryNotFoundException {
        this.inventoryService.delete(id);
    }


}
