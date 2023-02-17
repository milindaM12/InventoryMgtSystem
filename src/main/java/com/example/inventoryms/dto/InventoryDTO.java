package com.example.inventoryms.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class InventoryDTO {
    private int itemID;
    private String itemName;
    private String itemCategory;
    private String itemQty;
}
