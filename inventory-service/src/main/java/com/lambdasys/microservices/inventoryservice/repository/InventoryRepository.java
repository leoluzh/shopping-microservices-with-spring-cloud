package com.lambdasys.microservices.inventoryservice.repository;

import com.lambdasys.microservices.inventoryservice.model.Inventory;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InventoryRepository extends PagingAndSortingRepository<Inventory, Long> {

    Optional<Inventory> findBySku(String sku);
}
