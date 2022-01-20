package com.marinagaisina.inventoryapp.entities;


import com.marinagaisina.inventoryapp.service.ItemService;

import java.io.*;
import java.util.HashMap;
import java.util.Scanner;

public abstract class InventorySystem {
    private final HashMap<String, Item> itemCollection;

    public InventorySystem() throws IOException {
        this.itemCollection = new HashMap<>();

        if (this.getClass().getSimpleName().equals("ItemService")) {
            try {
                File inventoryFile = new File("/src/inventory.csv");
                //if (inventoryFile.isFile()) {
                String row;
                BufferedReader csvReader = new BufferedReader(new FileReader(inventoryFile));
                while ((row = csvReader.readLine()) != null) {
                    String[] data = row.split(",");
                    Item item = new Item(data[0], data[1], Double.parseDouble(data[2]), Integer.parseInt(data[3]));
                    this.getItemCollection().put(item.getItemName(), item);
                }
                System.out.println("The DB from inventory.csv uploaded successfully");
                csvReader.close();

            } catch (FileNotFoundException e) {
                System.out.println("Database *.csv file for loading data wasn't found. No data was loaded.");
            }
        }
    }

    public HashMap<String, Item> getItemCollection() {
        return itemCollection;
    }
    public Boolean add(Item item) {
        if (item == null) {
            return false;
        }
        if ((item.getItemName().length() == 0)||(item.getItemDesc().length()==0)||
                (item.getItemPrice() ==0)||(item.getAvailableQuantity()==0)) {
            System.out.println("Item name/Item description/Item price or Item Available Quantity one or all fields are incorrect.");
            throw new NumberFormatException("NumberFormatException");
        }
        if (this.getItemCollection().size() == 0) {
            this.getItemCollection().put(item.getItemName(), item);
            return true;
        }
        if (this.itemCollection.containsValue(item)) {
            Item foundItem = this.getItemCollection().get(item.getItemName());
            foundItem.setAvailableQuantity(item.getAvailableQuantity()+ item.getAvailableQuantity());
            System.out.println(foundItem.getItemName()+"'s Available quantity increased by "+
                    item.getAvailableQuantity()+".\nNow available quantity is "+foundItem.getAvailableQuantity());
        } else {
            this.getItemCollection().put(item.getItemName(), item);
            System.out.println(item.getItemName()+ " was successfully added to Inventory System.");
        }
        return true;
    }

    public Boolean add(Integer itemId) {
        return add(findItemById(itemId));
    }

    public Boolean add(String itemName) {
        return add(this.getItemCollection().get(itemName));
    }

    public Item remove(Item item) {
        if ((item == null)||(this.getItemCollection().size()==0)) return null;

        //if checkAvailability=> then reduceAvailability
        //if reduceAvailability - ok => remove

        return null;
    }

    public Item remove(String itemName) {
        if (this.getItemCollection().containsKey(itemName)) {
            return this.getItemCollection().remove(itemName);
        }
        return null;
    }
    public Item remove(Integer itemId) {
        return remove(findItemById(itemId));
    }

    public Item findItemById(Integer itemId) {
        for (Item currentItem : this.getItemCollection().values()) {
            if (currentItem.getItemId().equals(itemId)) {
                return this.getItemCollection().get(currentItem.getItemName());
            }
        }
        return null;
    }

    public abstract void display();

}

