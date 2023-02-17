package com.example.inventoryms.repo;

import com.example.inventoryms.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InventoryRepo extends JpaRepository<Inventory, Integer> {
    List<Inventory> findByItemCategory(String itemCategory);
}
