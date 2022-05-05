package com.example.bookstore;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.MyViewHolder> {
    Context context;
    ArrayList<CartItem> CartArrayList;

    public CartAdapter(Context context, ArrayList<CartItem> CartArrayList) {
        this.context = context;
        this.CartArrayList = CartArrayList;
    }

    @NonNull
    @Override
    public CartAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CartAdapter.MyViewHolder holder, int position) {
        CartItem cartItem = CartArrayList.get(position);
        holder.Title.setText(cartItem.Title);
        holder.Author.setText(cartItem.Author);
        holder.Price.setText(String.valueOf(cartItem.Price));
    }

    @Override
    public int getItemCount() {
        return CartArrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView Title, Author, Price;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            Title = itemView.findViewById(R.id.cartItemTitle);
            Author = itemView.findViewById(R.id.cartItemAuthor);
            Price = itemView.findViewById(R.id.cartItemPrice);
        }
    }
}