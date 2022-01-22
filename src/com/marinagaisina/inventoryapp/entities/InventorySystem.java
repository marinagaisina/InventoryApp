package com.marinagaisina.inventoryapp.entities;

import java.io.*;
import java.util.TreeMap;

public abstract class InventorySystem {
    private final TreeMap<String, Item> itemCollection;

    public InventorySystem() throws IOException {
        this.itemCollection = new TreeMap<>();
        //System.out.println("Would you like to load the data from the example file or let's start with an empty data base?");

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

    protected TreeMap<String, Item> getItemCollection() {
        return itemCollection;
    }

    /**
     * @description Boolean add(Item item) - adds an Item object to the inventory collection.
     * @param newItem - an object of Item
     * @return true 1) if Item was added to the inventory collection
     *              2) if the same Item was found (foundItem) and quantity was increased by available quantity value of a new Item
     * @throws NumberFormatException if a user tried to create an item with an empty name, or description or with price=0;
     * @details: reduces by 1 the static generated Id value in Item class if the newItem wasn't added to the collection
     */

    public Boolean add(Item newItem) {
        if (newItem == null) {
            return false;
        }
        // !! for future: create a method for item name validation check, it should not allow to create a name that contains numbers only
        if ((newItem.getItemName().length() == 0)||(newItem.getItemDesc().length()==0)||(newItem.getItemPrice() ==0)) {
            Item.setLastGeneratedID(Item.getLastGeneratedID()-1);
            System.out.println("Item name/Item description or Item price - one or all fields are incorrect.");
            throw new NumberFormatException("NumberFormatException");
        }
        if (this.itemCollection.containsValue(newItem)) {
            Item foundItem = this.getItemCollection().get(newItem.getItemName());
            foundItem.setAvailableQuantity(foundItem.getAvailableQuantity()+ newItem.getAvailableQuantity());
            System.out.println(foundItem.getItemName()+"'s Available quantity increased by "+
                    newItem.getAvailableQuantity()+".\nNow available quantity is "+foundItem.getAvailableQuantity());
            Item.setLastGeneratedID(Item.getLastGeneratedID()-1);
        } else {
            this.getItemCollection().put(newItem.getItemName(), newItem);
            System.out.println(newItem.getItemName()+ " was successfully added to Inventory System.");
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

