package com.mathocosta.learnbasic;

public class ShoppingListItem {
    private String name;
    private String email;
    private String imageURL;

    public ShoppingListItem(String name, String email, String imageURL) {
        this.name = name;
        this.email = email;
        this.imageURL = imageURL;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getImageURL() {
        return imageURL;
    }
}
