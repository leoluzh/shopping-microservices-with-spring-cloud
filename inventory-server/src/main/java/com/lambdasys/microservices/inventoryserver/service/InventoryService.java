package com.lambdasys.microservices.inventoryserver.service;

import com.lambdasys.microservices.inventoryserver.dto.InventoryDto;
import com.lambdasys.microservices.inventoryserver.exception.InventoryNotFoundException;
import com.lambdasys.microservices.inventoryserver.mapper.InventoryMapper;
import com.lambdasys.microservices.inventoryserver.model.Inventory;
import com.lambdasys.microservices.inventoryserver.repository.InventoryRepository;
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

}
