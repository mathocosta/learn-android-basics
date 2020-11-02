package com.mathocosta.learnbasic;

import java.io.Serializable;

public class ShoppingListItem implements Serializable {
    private String name;
    private String description;
    private boolean isChecked;

    private int quantity;

    public ShoppingListItem(String name, String description, int quantity, boolean isChecked) {
        this.name = name;
        this.description = description;
        this.isChecked = isChecked;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setChecked(boolean checked) {
        this.isChecked = checked;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
