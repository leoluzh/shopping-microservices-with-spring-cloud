package com.lambdasys.microservices.inventoryservice.service;

import com.lambdasys.microservices.inventoryservice.dto.InventoryDto;
import com.lambdasys.microservices.inventoryservice.exception.InventoryNotFoundException;
import com.lambdasys.microservices.inventoryservice.mapper.InventoryMapper;
import com.lambdasys.microservices.inventoryservice.model.Inventory;
import com.lambdasys.microservices.inventoryservice.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class InventoryService implements Serializable {

    private final InventoryRepository inventoryRepository;
    private final InventoryMapper inventoryMapper;

    public InventoryDto findById(Long id) throws InventoryNotFoundException {
        var inventory = verifyIfExists(id);
        return inventoryMapper.toDto(inventory);
    }

    public InventoryDto findBySku(String sku) throws InventoryNotFoundException {
        var inventory = verifyIfExists(sku);
        return inventoryMapper.toDto(inventory);
    }

    public List<InventoryDto> findAll(){
        return StreamSupport
                .stream(this.inventoryRepository.findAll().spliterator(),false)
                .map(inventoryMapper::toDto)
                .toList();
    }

    public Page<InventoryDto> findAll(final Pageable pageable){
        return this.inventoryRepository
                .findAll(pageable)
                .map(inventoryMapper::toDto);
    }

    public InventoryDto create(final InventoryDto inventoryDto){
        final var inventory = inventoryMapper.toEntity(inventoryDto);
        final var result = this.inventoryRepository.save(inventory);
        return this.inventoryMapper.toDto(result);
    }

    public InventoryDto update(final Long id, final InventoryDto inventoryDto) throws InventoryNotFoundException {
        final var inventory = verifyIfExists(id);
        this.inventoryMapper.update(inventoryDto, inventory);
        final var result = this.inventoryRepository.save(inventory);
        return this.inventoryMapper.toDto(result);
    }

    public void delete(final Long id) throws InventoryNotFoundException {
        final var inventory = verifyIfExists(id);
        this.inventoryRepository.delete(inventory);
    }

    public Inventory verifyIfExists(Long id) throws InventoryNotFoundException {
        return this.inventoryRepository
                .findById(id)
                .orElseThrow( () -> new InventoryNotFoundException(id));
    }

    public Inventory verifyIfExists(String sku) throws InventoryNotFoundException {
        return this.inventoryRepository
                .findBySku(sku)
                .orElseThrow( () -> new InventoryNotFoundException(sku));
    }



}
