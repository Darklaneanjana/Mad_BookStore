package com.example.bookstore;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    Context context;
    ArrayList<CartItem> cartItemArrayList;

    public CartAdapter(Context context, ArrayList<CartItem> cartItemArrayList) {
        this.context = context;
        this.cartItemArrayList = cartItemArrayList;
    }

    @NonNull
    @Override
    public CartAdapter.CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item9, parent, false);
        return new CartViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CartAdapter.CartViewHolder holder, int position) {
        CartItem cartItem = cartItemArrayList.get(position);
        holder.Title.setText(cartItem.Title);
        holder.Author.setText(cartItem.Author);
        holder.Price.setText(String.valueOf(cartItem.Price));


    }

    @Override
    public int getItemCount() {
        return cartItemArrayList.size();
    }

    public static class CartViewHolder extends RecyclerView.ViewHolder {

        TextView Title, Author, Price;

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            Title = itemView.findViewById(R.id.cartItemTitle);
            Author = itemView.findViewById(R.id.cartItemAuthor);
            Price = itemView.findViewById(R.id.cartItemPrice);
        }
    }
}
