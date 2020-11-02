package com.mathocosta.learnbasic.models;

import java.io.Serializable;
import java.util.ArrayList;

public class ShoppingList implements Serializable {
    private String name;
    private ArrayList<ShoppingListItem> items;

    public ShoppingList(String name) {
        this.name = name;
        this.items = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<ShoppingListItem> getItems() {
        return items;
    }

    public void addItem(ShoppingListItem item) {
        items.add(item);
    }

    public ArrayList<ShoppingListItem> getCheckedItems() {
        ArrayList<ShoppingListItem> checkedItems = new ArrayList<>();

        for (ShoppingListItem item : items) {
            if (item.isChecked()) {
                checkedItems.add(item);
            }
        }

        return checkedItems;
    }
}
