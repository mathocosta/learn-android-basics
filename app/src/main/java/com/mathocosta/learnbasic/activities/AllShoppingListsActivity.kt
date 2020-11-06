package com.mathocosta.learnbasic.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.mathocosta.learnbasic.R
import com.mathocosta.learnbasic.activities.adapters.AllShoppingListsRecViewAdapter
import com.mathocosta.learnbasic.activities.adapters.AllShoppingListsRecViewAdapter.ShoppingListClickListener
import com.mathocosta.learnbasic.databinding.ActivityAllShoppingListsBinding
import com.mathocosta.learnbasic.models.ShoppingList
import com.mathocosta.learnbasic.models.ShoppingListItem
import kotlinx.android.synthetic.main.activity_all_shopping_lists.*
import java.util.*

fun getFakeData(): MutableList<ShoppingList> = mutableListOf(
        ShoppingList("Primeira lista", mutableListOf<ShoppingListItem>(
                ShoppingListItem("Carne", "", 2, false)
        )),
        ShoppingList("Segunda lista", mutableListOf<ShoppingListItem>(
                ShoppingListItem("Leite", "", 2, false),
                ShoppingListItem("Frango", "", 1, false),
                ShoppingListItem("Maças", "", 2, false)
        ))
)

class AllShoppingListsActivity : AppCompatActivity(), ShoppingListClickListener {
    private lateinit var binding: ActivityAllShoppingListsBinding
    private val shoppingLists: MutableList<ShoppingList> = getFakeData()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAllShoppingListsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        binding.allListsRecyclerView.adapter = AllShoppingListsRecViewAdapter(shoppingLists, this)
    }

    override fun onShoppingListClick(shoppingList: ShoppingList) {
        val intent = Intent(this, ShoppingListActivity::class.java).apply {
            putExtra("SHOPPING_LIST", shoppingList)
        }

        startActivity(intent)
    }
}