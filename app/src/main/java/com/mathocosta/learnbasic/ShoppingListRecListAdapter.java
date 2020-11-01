package com.mathocosta.learnbasic;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.checkbox.MaterialCheckBox;

import java.util.ArrayList;

interface ShoppingListItemClickListener {
    void onShoppingListItemClick(ShoppingListItem item);
}

public class ShoppingListRecListAdapter extends RecyclerView.Adapter<ShoppingListRecListAdapter.ViewHolder> {

    private ArrayList<ShoppingListItem> shoppingListItems = new ArrayList<>();
    private final Context context;
    private final ShoppingListItemClickListener itemClickListener;

    public ShoppingListRecListAdapter(Context context, ShoppingListItemClickListener itemClickListener) {
        this.context = context;
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.shopping_list_item, parent, false);

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
        private final TextView txtDescription;
        private final MaterialCheckBox checkBox;
        private final CardView parent;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtName = itemView.findViewById(R.id.txtName);
            txtDescription = itemView.findViewById(R.id.txtDescription);
            checkBox = itemView.findViewById(R.id.checkbox);
            parent = itemView.findViewById(R.id.parent);
        }

        public void bindView(ShoppingListItem listItem, ShoppingListItemClickListener listener) {
            txtName.setText(listItem.getName());
            txtDescription.setText(listItem.getDescription());
            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    listItem.setChecked(isChecked);
                }
            });
            parent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onShoppingListItemClick(listItem);
                }
            });
        }
    }
}
