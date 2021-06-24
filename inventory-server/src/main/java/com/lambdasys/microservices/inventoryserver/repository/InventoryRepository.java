package com.lambdasys.microservices.inventoryserver.repository;

import com.lambdasys.microservices.inventoryserver.model.Inventory;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryRepository extends PagingAndSortingRepository<Inventory, Long> {
}
