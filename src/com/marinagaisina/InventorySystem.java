package com.marinagaisina;

import java.io.IOException;
import java.util.HashMap;

public abstract class InventorySystem {
    private HashMap<String,Item> itemCollection;

    public InventorySystem() throws IOException {
        this.itemCollection = new HashMap<>();
    }
}
