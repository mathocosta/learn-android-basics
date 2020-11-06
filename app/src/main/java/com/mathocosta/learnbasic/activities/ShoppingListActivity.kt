package com.mathocosta.learnbasic.activities

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.view.ActionMode
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.mathocosta.learnbasic.R
import com.mathocosta.learnbasic.activities.adapters.ShoppingListRecListAdapter
import com.mathocosta.learnbasic.activities.adapters.ShoppingListRecListAdapter.ItemClickListener
import com.mathocosta.learnbasic.databinding.ActivityShoppingListBinding
import com.mathocosta.learnbasic.models.ShoppingList
import com.mathocosta.learnbasic.models.ShoppingListItem
import java.util.*
import kotlin.collections.ArrayList

class ShoppingListActivity : AppCompatActivity(), ItemClickListener {

    private lateinit var recViewAdapter: ShoppingListRecListAdapter
    private lateinit var binding: ActivityShoppingListBinding
    private var actionMode: ActionMode? = null

    private val simpleTouchCallback: ItemTouchHelper.SimpleCallback = object :
            ItemTouchHelper.SimpleCallback(
                    ItemTouchHelper.DOWN or ItemTouchHelper.UP, 0
            ) {
        override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
        ): Boolean = recViewAdapter.onItemMoved(viewHolder.adapterPosition, target.adapterPosition)

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {}

        override fun onSelectedChanged(viewHolder: RecyclerView.ViewHolder?, actionState: Int) {
            super.onSelectedChanged(viewHolder, actionState)
            if (actionState == ItemTouchHelper.ACTION_STATE_DRAG) {
                viewHolder?.itemView?.alpha = 0.8f
            }
        }

        override fun clearView(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder) {
            super.clearView(recyclerView, viewHolder)
            viewHolder.itemView.alpha = 1.0f
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityShoppingListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()
        displayShoppingList()

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun setupRecyclerView() {
        recViewAdapter = ShoppingListRecListAdapter(this)
        binding.shoppingListRecView.adapter = recViewAdapter

        ItemTouchHelper(simpleTouchCallback).attachToRecyclerView(binding.shoppingListRecView)
    }

    private fun displayShoppingList() {
        val shoppingList = intent.getSerializableExtra("SHOPPING_LIST") as? ShoppingList ?: return
        recViewAdapter.apply {
            shoppingListItems = shoppingList.items
            notifyDataSetChanged()
        }
    }

    fun onAddActionButtonPressed(view: View?) {
        val intent = Intent(this, ShoppingItemFormActivity::class.java)
        startActivityForResult(intent, ADD_ITEM)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == RESULT_OK && data != null) {
            val resultItem = data.getSerializableExtra("ITEM") as ShoppingListItem? ?: return

            if (requestCode == ADD_ITEM) {
                recViewAdapter.shoppingListItems.add(resultItem)
            } else if (requestCode == EDIT_ITEM) {
                val itemIndex = data.getIntExtra("ITEM_ID", -1)
                if (itemIndex != -1) {
                    recViewAdapter.shoppingListItems[itemIndex] = resultItem
                }
            }

            recViewAdapter.notifyDataSetChanged()
        }
    }

    override fun onShoppingListItemClick(item: ShoppingListItem) {
        val intent = Intent(this, ShoppingItemFormActivity::class.java).apply {
            putExtra("ITEM", item)
            putExtra("ITEM_ID", recViewAdapter.shoppingListItems.indexOf(item))
        }

        startActivityForResult(intent, EDIT_ITEM)
    }

    override fun onShoppingListItemLongClick(item: ShoppingListItem) {
        if (actionMode == null) {
            actionMode = startSupportActionMode(object : ActionMode.Callback {
                override fun onCreateActionMode(mode: ActionMode?, menu: Menu?): Boolean {
                    mode?.menuInflater?.inflate(R.menu.menu_delete, menu)
                    return true
                }

                override fun onPrepareActionMode(mode: ActionMode?, menu: Menu?): Boolean {
                    return false
                }

                override fun onActionItemClicked(mode: ActionMode?, item: MenuItem?): Boolean {
                    if (item?.itemId == R.id.action_delete) {
                        mode?.finish()
                        return true
                    }

                    return false
                }

                override fun onDestroyActionMode(mode: ActionMode?) {
                    actionMode = null
                }
            })
        }
    }

    companion object {
        private const val ADD_ITEM = 1
        private const val EDIT_ITEM = 2
    }
}