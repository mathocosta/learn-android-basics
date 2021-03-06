package com.mathocosta.learnbasic.activities.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.mathocosta.learnbasic.R
import com.mathocosta.learnbasic.models.ShoppingList
import kotlinx.android.synthetic.main.shopping_list_item.view.*
import java.util.*

class AllShoppingListsRecViewAdapter(
        private val shoppingLists: MutableList<ShoppingList>,
        private val clickListener: ShoppingListClickListener
) : RecyclerView.Adapter<AllShoppingListsRecViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater
                .from(parent.context)
                .inflate(R.layout.shopping_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindView(shoppingLists[position], clickListener)
    }

    override fun getItemCount(): Int = shoppingLists.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val parent: CardView = itemView.findViewById(R.id.parent)

        fun bindView(shoppingList: ShoppingList, listener: ShoppingListClickListener) {
            itemView.txt_name.text = shoppingList.name

            val numberOfCheckedItems = shoppingList.checkedItems.size
            val numberOfItems = shoppingList.items.size
            itemView.txt_counter.text = "$numberOfCheckedItems/$numberOfItems"

            parent.setOnClickListener { listener.onShoppingListClick(shoppingList) }
        }

    }

    interface ShoppingListClickListener {
        fun onShoppingListClick(shoppingList: ShoppingList)
    }
}