package com.mathocosta.learnbasic.activities

import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.google.android.material.textfield.TextInputLayout
import com.mathocosta.learnbasic.R
import com.mathocosta.learnbasic.databinding.ActivityShoppingItemFormBinding
import com.mathocosta.learnbasic.models.ShoppingListItem

class ShoppingItemFormActivity : AppCompatActivity() {
    private var item: ShoppingListItem? = null
    private var itemID = 0

    private lateinit var binding: ActivityShoppingItemFormBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShoppingItemFormBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Prepare toolbar
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        prepareEditItem()
    }

    private fun prepareEditItem() {
        item = intent.getSerializableExtra("ITEM") as ShoppingListItem?
        itemID = intent.getIntExtra("ITEM_ID", -1)

        item?.let {
            binding.nameTextField.editText?.setText(it.name)
            binding.quantityTextField.editText?.setText(it.quantity.toString())
            binding.descriptionTextField.editText?.setText(it.name)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            setResult(RESULT_CANCELED)
            finish()
            return true
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    fun onSaveButtonPressed(view: View?) {
        val itemName = binding.nameTextField.editText?.text.toString()
        val itemDescription = binding.descriptionTextField.editText?.text.toString()
        val itemQuantityTextValue = binding.quantityTextField.editText?.text.toString()
        val itemQuantity = itemQuantityTextValue.toIntOrNull() ?: 1

        if (itemName.isEmpty()) {
            binding.nameTextField.error = "Campo obrigatório"
        } else {
            if (item != null) {
                item?.name = itemName
                item?.description = itemDescription
                item?.quantity = itemQuantity
            } else {
                item = ShoppingListItem(itemName, itemDescription, itemQuantity, false)
            }

            val intent = Intent().apply {
                putExtra("ITEM", item)
                putExtra("ITEM_ID", itemID)
            }

            setResult(RESULT_OK, intent)
            finish()
        }
    }
}