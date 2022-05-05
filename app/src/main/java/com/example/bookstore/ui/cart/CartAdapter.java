package com.example.bookstore.ui.cart;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookstore.R;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

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
        StorageReference storageReference = FirebaseStorage.getInstance().getReference().child("Books/" + cartItem.Image);
        // ImageView in your Activity
        // [END storage_load_with_glide]
        storageReference.getBytes(1024 * 1024 * 3).addOnSuccessListener(bytes -> {
            Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
            holder.Image.setImageBitmap(bitmap);
            // Data for "images/island.jpg" is returns, use this as needed
        }).addOnFailureListener(exception -> {
            Log.e("Firestore Error", "ta lllll");
            // Handle any errors
        });

    }

    @Override
    public int getItemCount() {
        return CartArrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView Title, Author, Price;
        ImageView Image;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            Title = itemView.findViewById(R.id.cartItemTitle);
            Author = itemView.findViewById(R.id.cartItemAuthor);
            Price = itemView.findViewById(R.id.cartItemPrice);
            Image = (ImageView) itemView.findViewById(R.id.cartItemImage);
        }
    }
}