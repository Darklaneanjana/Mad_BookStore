package com.example.bookstore.ui.cart;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookstore.R;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class CartFragment extends Fragment {

    RecyclerView recyclerView;
    ArrayList<CartItem> CartArrayList;
    CartAdapter cartAdapter;
    FirebaseFirestore db;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cart, container, false);

        db = FirebaseFirestore.getInstance();
        CartArrayList = new ArrayList<>();


        recyclerView = view.findViewById(R.id.recyclerView1);
        cartAdapter = new CartAdapter(getContext(), CartArrayList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(cartAdapter);

        EventChangeListener();
        return view;

    }


    private void EventChangeListener() {
        db.collection("Cart").document("WJhcfXZpxSYXqSQqR2ymcpY7fpP2").collection("Book").addSnapshotListener((value, error) -> {

            if (error != null) {
                Log.e("Firestore Error", error.getMessage());
                return;
            }
            assert value != null;
            for (DocumentChange dc : value.getDocumentChanges()) {
                if (dc.getType() == DocumentChange.Type.ADDED) {
                    CartItem crt = dc.getDocument().toObject(CartItem.class);
                    crt.setDocumentId(dc.getDocument().getId());
                    CartArrayList.add(crt);
                }
                cartAdapter.notifyDataSetChanged();
            }
        });
    }
}