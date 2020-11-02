package com.mathocosta.learnbasic.activities.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.mathocosta.learnbasic.R;
import com.mathocosta.learnbasic.models.ShoppingList;

import java.util.ArrayList;

public class AllShoppingListsRecViewAdapter
        extends RecyclerView.Adapter<AllShoppingListsRecViewAdapter.ViewHolder> {

    private ArrayList<ShoppingList> shoppingLists = new ArrayList<>();
    private final ShoppingListClickListener clickListener;

    public AllShoppingListsRecViewAdapter(ShoppingListClickListener clickListener) {
        this.clickListener = clickListener;
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
        holder.bindView(shoppingLists.get(position), clickListener);
    }

    @Override
    public int getItemCount() {
        return shoppingLists.size();
    }

    public void setShoppingLists(ArrayList<ShoppingList> shoppingLists) {
        this.shoppingLists = shoppingLists;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView txtName, txtCounter;
        private final CardView parent;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtName = itemView.findViewById(R.id.txt_name);
            txtCounter = itemView.findViewById(R.id.txt_counter);
            parent = itemView.findViewById(R.id.parent);
        }

        public void bindView(ShoppingList shoppingList, ShoppingListClickListener listener) {
            txtName.setText(shoppingList.getName());
            txtCounter.setText(
                    String.format(
                            "%d/%d",
                            shoppingList.getCheckedItems().size(),
                            shoppingList.getItems().size()
                    )
            );
            parent.setOnClickListener(view -> listener.onShoppingListClick(shoppingList));
        }
    }

    public interface ShoppingListClickListener {
        void onShoppingListClick(ShoppingList shoppingList);
    }
}
