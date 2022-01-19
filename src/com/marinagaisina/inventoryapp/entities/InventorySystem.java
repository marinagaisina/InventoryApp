package com.marinagaisina.inventoryapp.entities;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public abstract class InventorySystem {
    private HashMap<String, Item> itemCollection;

    public InventorySystem() throws IOException {
        this.itemCollection = new HashMap<>();

        if (this.getClass().getSimpleName().equals("ItemService")) {
            File inventoryFile = new File("/src/sample.csv");
            //if (inventoryFile.isFile()) {
                String row;
                BufferedReader csvReader = new BufferedReader(new FileReader("/src/sample.csv"));
                while ((row = csvReader.readLine()) != null) {
                    String[] data = row.split(",");
                    // do something with the data
                }
                csvReader.close();
            //}


//            File readInFile = new File("/src/resources/sample.csv");
//            Scanner scanner = new Scanner(readInFile);
//            while (scanner.hasNextLine()) {
                // scanner.useDelimiter(" {2}");
//                String[] itemInfo = scanner.nextLine().split(" {2}");
//                Item item = new Item(itemInfo[0], itemInfo[1], Double.parseDouble(itemInfo[2]), Integer.parseInt(itemInfo[3]));
//                item.setQuantity(1);
//                this.getItemCollection().put(item.getItemName(), item);
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

    public Item remove(Item item, Integer removeQuantity) {
        if ((item == null)||(this.getItemCollection().size()==0)) return null;

        //if checkAvailability=> then reduceAvailability
        //if reduceAvailability - ok => remove

        return null;
    }
    public Item remove(Item item) {
        return remove(item,1);
    }

    public Item remove(String itemName) {
        if (this.getItemCollection().containsKey(itemName)) {
            return this.getItemCollection().remove(itemName);
        }
        return null;
    }
    public Item remove(Integer itemId) {
        return findItemById(itemId);
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

