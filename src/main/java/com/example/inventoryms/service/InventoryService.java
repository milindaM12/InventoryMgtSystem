package com.example.inventoryms.service;

import com.example.inventoryms.dto.InventoryDTO;
import com.example.inventoryms.entity.Inventory;
import com.example.inventoryms.repo.InventoryRepo;
import com.example.inventoryms.util.VarList;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class InventoryService {
    @Autowired
    private InventoryRepo inventoryRepo;
    @Autowired
    private ModelMapper modelMapper;


    public String saveItem(InventoryDTO inventoryDTO){
        if(inventoryRepo.existsById(inventoryDTO.getItemID())){
            return VarList.RSP_DUPLICATED;
        }else{
            inventoryRepo.save(modelMapper.map(inventoryDTO, Inventory.class));
            return VarList.RSP_SUCCESS;
        }
    }
    public String updateItem(InventoryDTO inventoryDTO){
        if (inventoryRepo.existsById(inventoryDTO.getItemID())){
            inventoryRepo.save(modelMapper.map(inventoryDTO,Inventory.class));
            return VarList.RSP_SUCCESS;
        }else{
            return VarList.RSP_NO_DATA_FOUND;
        }
    }

    public List<InventoryDTO> getAllItems(){
        List<Inventory> itemList = inventoryRepo.findAll();
        return modelMapper.map(itemList, new TypeToken<ArrayList<InventoryDTO>>(){

        }.getType());
    }

    public InventoryDTO searchItem(int itemID){
        if (inventoryRepo.existsById(itemID)){
            Inventory inventory = inventoryRepo.findById(itemID).orElse(null);
            return modelMapper.map(inventory,InventoryDTO.class);
        }else{
            return null;
        }
    }

    public String deleteItem(int itemID){
        if (inventoryRepo.existsById(itemID)){
            inventoryRepo.deleteById(itemID);
            return VarList.RSP_SUCCESS;
        }else{
            return VarList.RSP_NO_DATA_FOUND;
        }
    }
    public List<Inventory> getInventoryByCategory(String category){
        return inventoryRepo.findByItemCategory(category);
    }
}
