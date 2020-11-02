package com.mathocosta.learnbasic;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;

public class ShoppingItemFormActivity extends AppCompatActivity {

    private TextInputLayout nameTextField, quantityTextField, descriptionTextField;
    private ShoppingListItem item;
    private int itemID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_item_form);

        nameTextField = findViewById(R.id.nameTextField);
        quantityTextField = findViewById(R.id.quantityTextField);
        descriptionTextField = findViewById(R.id.descriptionTextField);

        Intent intent = getIntent();
        item = (ShoppingListItem) intent.getSerializableExtra("ITEM");
        itemID = intent.getIntExtra("ITEM_ID", -1);
        if (item != null) {
            nameTextField.getEditText().setText(item.getName().toString());
            quantityTextField.getEditText().setText(String.valueOf(item.getQuantity()));
            descriptionTextField.getEditText().setText(item.getName().toString());
        }
    }

    public void onSaveButtonPressed(View view) {
        String itemName = nameTextField.getEditText().getText().toString();
        String itemDescription = descriptionTextField.getEditText().getText().toString();

        String itemQuantityValue = quantityTextField.getEditText().getText().toString();
        int itemQuantity;
        if (itemQuantityValue.isEmpty()) {
            itemQuantity = 1;
        } else {
            itemQuantity = Integer.parseInt(itemQuantityValue);
        }

        if (itemName.isEmpty()) {
            nameTextField.setError("Campo obrigatório");
        } else {
            if (item != null) {
                item.setName(itemName);
                item.setDescription(itemDescription);
                item.setQuantity(itemQuantity);
            } else {
                item = new ShoppingListItem(itemName, itemDescription, itemQuantity, false);
            }

            Intent intent = new Intent();
            intent.putExtra("ITEM", item);
            intent.putExtra("ITEM_ID", itemID);
            setResult(RESULT_OK, intent);
            finish();
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            setResult(RESULT_CANCELED);
            finish();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}