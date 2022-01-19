package com.marinagaisina.inventoryapp.entities;


import java.io.*;
import java.util.HashMap;
import java.util.Scanner;

public abstract class InventorySystem {
    private HashMap<String, Item> itemCollection;

    public InventorySystem() throws IOException {
        this.itemCollection = new HashMap<>();

        if (this.getClass().getSimpleName().equals("ItemService")) {
            try {
                File inventoryFile = new File("/src/inventory.csv");
                //if (inventoryFile.isFile()) {
                String row;
                BufferedReader csvReader = new BufferedReader(new FileReader("/src/inventory.csv"));
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
    public Boolean add(Item item, int addQuantity) {
        if (item == null) {
            return false;
        }
        if (this.getItemCollection().size() == 0) {
            this.getItemCollection().put(item.getItemName(), item);
            return true;
        }
        if (this.itemCollection.containsValue(item)) {
            Item foundItem = this.getItemCollection().get(item.getItemName());
            foundItem.setAvailableQuantity(foundItem.getAvailableQuantity()+addQuantity);
        } else {
            this.getItemCollection().put(item.getItemName(), item);
        }
        return true;
    }

    public Boolean add(Item item) {
        return add(item,1);
    }
    public Boolean add(Integer itemId, Integer quantity) {
        return add(findItemById(itemId), quantity);
    }
    public Boolean add(Integer itemId) {
        return add(findItemById(itemId));
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

