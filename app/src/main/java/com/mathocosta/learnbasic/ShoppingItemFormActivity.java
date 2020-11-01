package com.mathocosta.learnbasic;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;

public class ShoppingItemFormActivity extends AppCompatActivity {

    TextInputLayout nameTextField, quantityTextField, descriptionTextField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_item_form);

        nameTextField = findViewById(R.id.nameTextField);
        quantityTextField = findViewById(R.id.quantityTextField);
        descriptionTextField = findViewById(R.id.descriptionTextField);
    }

    public void onSaveButtonPressed(View view) {
        String itemName = nameTextField.getEditText().getText().toString();

        if (itemName.isEmpty()) {
            nameTextField.setError("Campo obrigatório");
        } else {
            Intent intent = new Intent();
            intent.putExtra("NEW_ITEM_NAME", itemName);
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