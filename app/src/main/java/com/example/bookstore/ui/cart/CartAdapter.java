package com.example.bookstore.ui.cart;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookstore.R;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.MyViewHolder> {
    private static final String TAG = "EmailPassword";
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
        holder.Price.setText(cartItem.Price + "$");
        holder.ItemCount.setText(String.valueOf(cartItem.Count));

        holder.cartRemoveItem.setOnClickListener(view -> cartRemoveItem(cartItem.getDocumentId()));

        holder.ItemIncrement.setOnClickListener(view -> incrementCartItem(cartItem.getDocumentId(), cartItem.getCount()));
        holder.ItemDecrement.setOnClickListener(view -> decrementCartItem(cartItem.getDocumentId(), cartItem.getCount()));


        StorageReference storageReference = FirebaseStorage.getInstance().getReference().child("Books/" + cartItem.Image);
        // ImageView in your Activity
        // [END storage_load_with_glide]
        storageReference.getBytes(1024 * 1024 * 3).addOnSuccessListener(bytes -> {
            Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
            holder.Image.setImageBitmap(bitmap);
            // Data for "images/island.jpg" is returns, use this as needed
        }).addOnFailureListener(exception -> {
            Log.e("Firestore Error", "Remove Item Failed");
            // Handle any errors
        });
    }

    @Override
    public int getItemCount() {
        return CartArrayList.size();
    }

    private void incrementCartItem(String id, long count) {

        long countItem = count;
        if (countItem >= 10.0) {
            Toast.makeText(context, "Must be less than ten books", Toast.LENGTH_LONG).show();
        } else {

            countItem++;
            // Set the "isCapital" field of the city 'DC'
            FirebaseFirestore.getInstance().collection("Cart").document("WJhcfXZpxSYXqSQqR2ymcpY7fpP2").collection("Book").document(id)
                    .update("Count", countItem)
                    .addOnSuccessListener(aVoid -> Log.d(TAG, "DocumentSnapshot successfully updated!"))
                    .addOnFailureListener(e -> Log.w(TAG, "Error updating document", e));
        }
    }

    private void decrementCartItem(String id, long count) {
        long countItem = count;
        if (countItem <= 1.0) {
            Toast.makeText(context, "Must be more than one book", Toast.LENGTH_LONG).show();
        } else {

            countItem--;
            // Set the "isCapital" field of the city 'DC'
            FirebaseFirestore.getInstance().collection("Cart").document("WJhcfXZpxSYXqSQqR2ymcpY7fpP2").collection("Book").document(id)
                    .update("Count", countItem)
                    .addOnSuccessListener(aVoid -> Log.d(TAG, "DocumentSnapshot successfully updated!"))
                    .addOnFailureListener(e -> Log.w(TAG, "Error updating document", e));
        }
    }

    public void cartRemoveItem(String id) {
        FirebaseFirestore.getInstance().collection("Cart").document("WJhcfXZpxSYXqSQqR2ymcpY7fpP2").collection("Book").document(id)
                .delete()
                .addOnSuccessListener(aVoid -> {
                    Log.d(TAG, "DocumentSnapshot successfully deleted!");
                    notifyDataSetChanged();
                    Toast.makeText(context, "The Book Successfully Removed from cart", Toast.LENGTH_LONG).show();
                })
                .addOnFailureListener(e -> Log.w(TAG, "Error deleting document", e));
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView Title, Author, Price, ItemCount;
        ImageView Image;
        ImageButton cartRemoveItem;
        Button ItemIncrement, ItemDecrement;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            Title = itemView.findViewById(R.id.cartItemTitle);
            Author = itemView.findViewById(R.id.cartItemAuthor);
            Price = itemView.findViewById(R.id.cartItemPrice);
            Image = itemView.findViewById(R.id.cartItemImage);
            cartRemoveItem = itemView.findViewById(R.id.cartItemDelete);
            ItemIncrement = itemView.findViewById(R.id.cartItemIncrement);
            ItemDecrement = itemView.findViewById(R.id.cartItemDecrement);
            ItemCount = itemView.findViewById(R.id.cartItemCount);
        }
    }
}