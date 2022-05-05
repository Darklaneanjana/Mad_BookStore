package com.example.bookstore;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

import javax.annotation.Nullable;

public class CartActivity extends AppCompatActivity {
    private static final String TAG = "EmailPassword";

    RecyclerView recyclerView;
    ArrayList<CartItem> CartArrayList;
    CartAdapter cartAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        CartArrayList = new ArrayList<CartItem>();
        cartAdapter = new CartAdapter(CartActivity.this, CartArrayList);

        loadCart("kkkk");

    }

    public void loadCart(String bid) {
        CollectionReference docRef = FirebaseFirestore.getInstance().collection("Cart").document("WJhcfXZpxSYXqSQqR2ymcpY7fpP2").collection("Book");
        docRef.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value,
                                @Nullable FirebaseFirestoreException e) {
                if (e != null) {
                    Log.w(TAG, "Listen failed.", e);
                    return;
                }


                for (DocumentChange doc : value.getDocumentChanges()) {
                    if (doc.getType() == DocumentChange.type.ADDED) {
                        CartArrayList.add(doc.getData().toObject(CartItem.class));

                    }
                }
//                for (QueryDocumentSnapshot doc : value) {
//                    if (doc.get("Title") != null) {
//                        Log.d(TAG, "Current cites in CA: " + doc.getData());
////                        CartArrayList.add(doc.getData().toObject(CartItem.class));
////                                cities.add(doc.getString("name"));
//                    }
//                }
//                        Log.d(TAG, "Current cites in CA: " + cities);
            }
        });
    }
}