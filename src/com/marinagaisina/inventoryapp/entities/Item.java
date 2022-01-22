package com.marinagaisina.inventoryapp.entities;

import jdk.jfr.Description;

import java.util.Objects;

public class Item {
    private static int lastGeneratedID=1;
    private Integer itemId;
    private String itemName;
    private String itemDesc;
    private Double itemPrice;
    private Integer cartQuantity;
    private Integer availableQuantity;

    public Item(String itemName, String itemDesc, Double itemPrice, Integer availableQuantity) {
        this.itemId=lastGeneratedID++;
        this.itemName = itemName;
        this.itemDesc = itemDesc;
        this.itemPrice = itemPrice;
        this.availableQuantity = availableQuantity;
        this.cartQuantity = 1;
    }

    public static int getLastGeneratedID() {
        return lastGeneratedID;
    }

    public static void setLastGeneratedID(int setID) {
        Item.lastGeneratedID = setID;
    }

    public Item() {
        this.itemId += 1;
        this.cartQuantity = 1;
    }

    public Integer getItemId() {
        return itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public String getItemDesc() {
        return itemDesc;
    }

    public Double getItemPrice() {
        return itemPrice;
    }

    public Integer getCartQuantity() {
        return cartQuantity;
    }

    public Integer getAvailableQuantity() {
        return availableQuantity;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public void setItemDesc(String itemDesc) {
        this.itemDesc = itemDesc;
    }

    public void setItemPrice(Double itemPrice) {
        this.itemPrice = itemPrice;
    }

    public void setCartQuantity(Integer cartQuantity) {
        this.cartQuantity = cartQuantity;
    }

    public void setAvailableQuantity(Integer availableQuantity) {
        this.availableQuantity = availableQuantity;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getItemId()+getItemName()+getItemDesc());
    }

    /**
     * Items are equal if they have the same Name, Description and Price (only)
     */

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Item)) return false;
        Item item = (Item) o;
        return getItemName().equals(item.getItemName())
                && getItemDesc().equals(item.getItemDesc())
                && getItemPrice().equals(item.getItemPrice());
    }
    @Override
    public String toString() {
        return super.toString();
    }
}
