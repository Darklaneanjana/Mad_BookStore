package com.example.bookstore;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class CartActivity extends AppCompatActivity {
    private static final String TAG = "EmailPassword";

    RecyclerView recyclerView;
    ArrayList<CartItem> CartArrayList;
    CartAdapter cartAdapter;
    FirebaseFirestore db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

    recyclerView = findViewById(R.id.recyclerView);
    recyclerView.setHasFixedSize(true);
    recyclerView.setLayoutManager(new LinearLayoutManager(this));

    db = FirebaseFirestore.getInstance();
        CartArrayList = new ArrayList<CartItem>();
    cartAdapter = new CartAdapter(CartActivity.this, CartArrayList);
    recyclerView.setAdapter(cartAdapter);
    EventChangeListner();


    }


    private void EventChangeListner() {
        db.collection("Cart").document("WJhcfXZpxSYXqSQqR2ymcpY7fpP2").collection("Book").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {

                if(error != null){
                    Log.e("Firestore Error",error.getMessage());
                    return;
                }
                for(DocumentChange dc:value.getDocumentChanges()){
                    if(dc.getType() == DocumentChange.Type.ADDED){
                        CartArrayList.add(dc.getDocument().toObject(CartItem.class));
                    }
                    cartAdapter.notifyDataSetChanged();
                }
            }
        });
    }

}