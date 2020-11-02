package com.mathocosta.learnbasic.activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mathocosta.learnbasic.R;
import com.mathocosta.learnbasic.activities.adapters.AllShoppingListsRecViewAdapter;
import com.mathocosta.learnbasic.models.ShoppingList;

import java.util.ArrayList;

public class AllShoppingListsActivity
        extends AppCompatActivity
        implements AllShoppingListsRecViewAdapter.ShoppingListClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_shopping_lists);
        setupRecyclerView();
    }

    public void setupRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.all_lists_recycler_view);

        AllShoppingListsRecViewAdapter recViewAdapter = new AllShoppingListsRecViewAdapter(this);
        recyclerView.setAdapter(recViewAdapter);

        ArrayList<ShoppingList> shoppingLists = new ArrayList<ShoppingList>();
        shoppingLists.add(new ShoppingList("Listinha"));

        recViewAdapter.setShoppingLists(shoppingLists);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void onShoppingListClick(ShoppingList shoppingList) {
        Intent intent = new Intent(this, ShoppingListActivity.class);
        intent.putExtra("SHOPPING_LIST", shoppingList);
        startActivity(intent);
    }
}
