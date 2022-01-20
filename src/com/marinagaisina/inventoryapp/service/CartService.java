package com.marinagaisina.inventoryapp.service;

import com.marinagaisina.inventoryapp.entities.InventorySystem;
import com.marinagaisina.inventoryapp.entities.Item;

import java.io.IOException;

public class CartService extends InventorySystem {
    public CartService() throws IOException {
        super();
    }

    @Override
    public void display() {
        double preTax = 0D;
        System.out.println("Cart:");
        System.out.format("%-10s %-20s %-20s %-10s %-10s %-10s\n", "ItemId","Name", "Description", "Price", "Quantity", "Sub Total");
        for (String name : this.getItemCollection().keySet()) {
            Item item = this.getItemCollection().get(name);
            preTax += item.getItemPrice()*item.getCartQuantity();
            System.out.printf("%-10d %-20s %-20s %-10s %-10s %-10s\n", item.getItemId(), name, item.getItemDesc(), item.getItemPrice(), item.getCartQuantity(),item.getCartQuantity()*item.getItemPrice());
        }
        System.out.format("%-20s %-20.2f\n", "Pre-tax Total", preTax);
        System.out.format("%-20s %-20.2f\n", "Tax", preTax*0.05);
        System.out.format("%-20s %-20.2f\n", "Total", preTax+preTax*0.05);
    }
}
