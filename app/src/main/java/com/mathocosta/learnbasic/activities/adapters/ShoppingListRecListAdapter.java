package com.mathocosta.learnbasic.activities.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.checkbox.MaterialCheckBox;
import com.mathocosta.learnbasic.R;
import com.mathocosta.learnbasic.models.ShoppingListItem;

import java.util.ArrayList;

public class ShoppingListRecListAdapter
        extends RecyclerView.Adapter<ShoppingListRecListAdapter.ViewHolder> {

    private ArrayList<ShoppingListItem> shoppingListItems = new ArrayList<>();
    private final ItemClickListener itemClickListener;

    public ShoppingListRecListAdapter(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.shopping_list_item_row, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ShoppingListItem listItem = shoppingListItems.get(position);
        holder.bindView(listItem, itemClickListener);
    }

    @Override
    public int getItemCount() {
        return shoppingListItems.size();
    }

    public void setItems(ArrayList<ShoppingListItem> items) {
        this.shoppingListItems = items;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView txtName;
        private final MaterialCheckBox checkBox;
        private final RelativeLayout parent;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtName = itemView.findViewById(R.id.txt_name);
            checkBox = itemView.findViewById(R.id.checkbox);
            parent = itemView.findViewById(R.id.parent);
        }

        public void bindView(ShoppingListItem listItem, ItemClickListener listener) {
            txtName.setText(String.format("%dx %s", listItem.getQuantity(), listItem.getName()));
            checkBox.setOnCheckedChangeListener(
                    (buttonView, isChecked) -> listItem.setChecked(isChecked)
            );
            parent.setOnClickListener(v -> listener.onShoppingListItemClick(listItem));
        }
    }

    public interface ItemClickListener {
        void onShoppingListItemClick(ShoppingListItem item);
    }
}
