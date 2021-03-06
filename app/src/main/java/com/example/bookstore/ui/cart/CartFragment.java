package com.example.bookstore.ui.cart;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookstore.R;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class CartFragment extends Fragment {

    public long Total = 0;
    public long ItemCount = 0;
    RecyclerView recyclerView;
    ArrayList<CartItem> CartArrayList;
    CartAdapter cartAdapter;
    FirebaseFirestore db;
    TextView cartItemCount;
    TextView cartTotalPrice;

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
        cartItemCount = view.findViewById(R.id.cartItemCount);
        cartTotalPrice = view.findViewById(R.id.cartTotal);


        EventChangeListener();
        return view;

    }


    public void EventChangeListener() {

        db.collection("Cart").document("WJhcfXZpxSYXqSQqR2ymcpY7fpP2").collection("Book").addSnapshotListener((value, error) -> {

            if (error != null) {
                Log.e("Firestore Error", error.getMessage());
                return;
            }
            assert value != null;
            ItemCount = 0;
            for (DocumentChange dc : value.getDocumentChanges()) {
                if (dc.getType() == DocumentChange.Type.ADDED) {
                    dc.getDocument().getData();
                    Log.d("Document Id", dc.getDocument().getId());
                    CartItem crt = dc.getDocument().toObject(CartItem.class);
                    crt.setDocumentId(dc.getDocument().getId());
                    CartArrayList.add(crt);

                    ItemCount += (long) dc.getDocument().getData().get("Count");
                    Total += (double) dc.getDocument().getData().get("price") * (long) dc.getDocument().getData().get("Count");
                }

                cartAdapter.notifyDataSetChanged();
            }
            cartItemCount.setText(String.valueOf(ItemCount));
            cartTotalPrice.setText(Total + "$");

        });
    }
    public void refresh(){
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        if (Build.VERSION.SDK_INT >= 26) {
            ft.setReorderingAllowed(false);
        }
        ft.detach(this).attach(this).commit();
    }
}