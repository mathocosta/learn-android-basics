package com.mathocosta.learnbasic;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class ShoppingListActivity extends AppCompatActivity implements ShoppingListItemClickListener {

    private static final int ADD_ITEM = 1;
    private static final int EDIT_ITEM = 2;

    private ShoppingListRecListAdapter recViewAdapter;
    private ArrayList<ShoppingListItem> shoppingListItems = new ArrayList<ShoppingListItem>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupRecyclerView();
    }

    private void setupRecyclerView() {
        RecyclerView shoppingListRecView = findViewById(R.id.shoppingListRecView);

        recViewAdapter = new ShoppingListRecListAdapter(this, this);
        shoppingListRecView.setAdapter(recViewAdapter);

        shoppingListRecView.setLayoutManager(new LinearLayoutManager(this));

        ArrayList<ShoppingListItem> savedShoppingList = getSavedShoppingList();
        if (savedShoppingList != null) {
            this.shoppingListItems = savedShoppingList;
            recViewAdapter.setItems(shoppingListItems);
        }
    }

    public void onAddActionButtonPressed(View view) {
        Intent intent = new Intent(this, ShoppingItemFormActivity.class);
        startActivityForResult(intent, ADD_ITEM);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            ShoppingListItem resultItem = (ShoppingListItem) data.getSerializableExtra("ITEM");

            if (resultItem == null) {
                return;
            }

            if (requestCode == ADD_ITEM) {
                shoppingListItems.add(resultItem);
            } else if (requestCode == EDIT_ITEM) {
                int itemIndex = data.getIntExtra("ITEM_ID", -1);
                if (itemIndex != -1) {
                    shoppingListItems.set(itemIndex, resultItem);
                }
            }

            recViewAdapter.setItems(shoppingListItems);
            saveShoppingList(shoppingListItems);
        }
    }

    @Override
    public void onShoppingListItemClick(ShoppingListItem item) {
        Intent intent = new Intent(this, ShoppingItemFormActivity.class);
        intent.putExtra("ITEM", item);
        intent.putExtra("ITEM_ID", shoppingListItems.indexOf(item));
        startActivityForResult(intent, EDIT_ITEM);
    }

    private ArrayList<ShoppingListItem> getSavedShoppingList() {
        SharedPreferences sharedPreferences = getPreferences(Context.MODE_PRIVATE);
        String savedJSON = sharedPreferences.getString("shopping_list", "");
        Gson gson = new Gson();
        Type shoppingListType = new TypeToken<ArrayList<ShoppingListItem>>() {
        }.getType();
        return gson.fromJson(savedJSON, shoppingListType);
    }

    private void saveShoppingList(ArrayList<ShoppingListItem> actualShoppingList) {
        SharedPreferences sharedPreferences = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String shoppingListJSON = gson.toJson(actualShoppingList);
        editor.putString("shopping_list", shoppingListJSON);
        editor.apply();
    }
}