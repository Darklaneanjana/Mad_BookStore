package com.example.bookstore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Objects;

public class DeliveryActivity extends AppCompatActivity {

    private static final String TAG = "Name";
    DocumentSnapshot document;
    String userName;
    private EditText edit_name,edit_address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery);

        String currentUser = FirebaseAuth.getInstance().getCurrentUser().getUid();

        DocumentReference docRef1 = FirebaseFirestore.getInstance().collection("Users").document(currentUser);
        docRef1.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                document = task.getResult();
                if (document.exists()) {
                    Log.d(TAG, "DocumentSnapshot data: " + Objects.requireNonNull(document.getData()));

                    userName=document.getData().get("Name").toString();
                }else {
                    Log.d(TAG, "No such document");
                }
            } else {
                Log.d(TAG, "get failed with ", task.getException());
            }
        });
        edit_name = findViewById(R.id.edit_name);
        edit_address = findViewById(R.id.edit_address);
        Button btn = findViewById(R.id.btn_submit);
       // DAODelivery dao = new DAODelivery();

        btn.setOnClickListener(v->
        {
            String txtName=edit_name.getText().toString();
            String txtAddress=edit_address.getText().toString();
        final HashMap<String, Object> DeliveryMap = new HashMap<>();
                DeliveryMap.put("Name",txtName);
                DeliveryMap.put("Address", txtAddress);

                FirebaseFirestore db = FirebaseFirestore.getInstance();

                db.collection("Delivery").document( currentUser)
                        .set(DeliveryMap)
                        .addOnSuccessListener(documentReference -> {
                            Toast.makeText(this, "Delivery added successfully", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent( getApplicationContext(),DeliveryViewActivity.class);
                            startActivity(intent);
                        })
                        .addOnFailureListener(e -> Log.w(TAG, "Error adding document", e));

            });

    }


}