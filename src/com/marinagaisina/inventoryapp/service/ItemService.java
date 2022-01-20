package com.marinagaisina.inventoryapp.service;

import com.marinagaisina.inventoryapp.entities.InventorySystem;
import com.marinagaisina.inventoryapp.entities.Item;

import java.io.IOException;

public class ItemService extends InventorySystem {
    public ItemService() throws IOException {
        super();
    }


    @Override
    public void display() {
        System.out.println("Inventory:");
        System.out.format("%-10s %-20s %-30s %-10s %-10s\n", "Item Id","Name", "Description", "Price", "Available Quantity");
        for (String name : this.getItemCollection().keySet()) {
            Item item = this.getItemCollection().get(name);
            System.out.format("%-10d %-20s %-30s %-10s %-10s\n", item.getItemId(), item.getItemName(), item.getItemDesc(), item.getItemPrice(), item.getAvailableQuantity());
        }
    }
}
