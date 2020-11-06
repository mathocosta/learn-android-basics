package com.mathocosta.learnbasic.activities.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import android.widget.RelativeLayout
import androidx.recyclerview.widget.RecyclerView
import com.mathocosta.learnbasic.R
import com.mathocosta.learnbasic.models.ShoppingListItem
import kotlinx.android.synthetic.main.shopping_list_item.view.txt_name
import kotlinx.android.synthetic.main.shopping_list_item_row.view.*
import java.util.*

class ShoppingListRecListAdapter(
        private val itemClickListener: ItemClickListener
) : RecyclerView.Adapter<ShoppingListRecListAdapter.ViewHolder>() {

    var shoppingListItems: MutableList<ShoppingListItem> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater
                .from(parent.context)
                .inflate(R.layout.shopping_list_item_row, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val listItem = shoppingListItems[position]
        holder.bindView(listItem, itemClickListener)
    }

    override fun getItemCount(): Int = shoppingListItems.size

    fun onItemMoved(fromPosition: Int, toPosition: Int): Boolean {
        Collections.swap(shoppingListItems, fromPosition, toPosition)
        notifyItemMoved(fromPosition, toPosition)

        return true
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindView(listItem: ShoppingListItem, listener: ItemClickListener) {
            itemView.checkbox.text = "${listItem.quantity} ${listItem.name}"
            itemView.checkbox.setOnCheckedChangeListener { _: CompoundButton?, isChecked: Boolean ->
                listItem.isChecked = isChecked
            }

            val parent = itemView.findViewById<RelativeLayout>(R.id.parent)
            parent.setOnClickListener { listener.onShoppingListItemClick(listItem) }
            parent.setOnLongClickListener {
                listener.onShoppingListItemLongClick(listItem)
                return@setOnLongClickListener true
            }
        }
    }

    interface ItemClickListener {
        fun onShoppingListItemClick(item: ShoppingListItem)
        fun onShoppingListItemLongClick(item: ShoppingListItem)
    }
}