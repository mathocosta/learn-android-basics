package com.mathocosta.learnbasic.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.mathocosta.learnbasic.R
import com.mathocosta.learnbasic.activities.adapters.AllShoppingListsRecViewAdapter
import com.mathocosta.learnbasic.activities.adapters.AllShoppingListsRecViewAdapter.ShoppingListClickListener
import com.mathocosta.learnbasic.models.ShoppingList
import kotlinx.android.synthetic.main.activity_all_shopping_lists.*
import java.util.*

class AllShoppingListsActivity : AppCompatActivity(), ShoppingListClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all_shopping_lists)
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        val recyclerView = findViewById<RecyclerView>(R.id.all_lists_recycler_view)
        val recViewAdapter = AllShoppingListsRecViewAdapter(this)
        recyclerView.adapter = recViewAdapter
        val shoppingLists = ArrayList<ShoppingList>()
        shoppingLists.add(ShoppingList("Listinha"))
        recViewAdapter.setShoppingLists(shoppingLists)
    }

    override fun onShoppingListClick(shoppingList: ShoppingList) {
        val intent = Intent(this, ShoppingListActivity::class.java)
        intent.putExtra("SHOPPING_LIST", shoppingList)
        startActivity(intent)
    }
}