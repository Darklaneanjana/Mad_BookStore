package com.example.bookstore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Objects;


public class DeliveryViewActivity extends AppCompatActivity {
    private static final String TAG = "Name";
    String txtName,txtAddress;
    DocumentSnapshot document;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery_view);

        EditText Edit_name = (EditText) findViewById(R.id.edit_name);
        EditText Edit_address = (EditText) findViewById(R.id.edit_address);

        Button Update = findViewById(R.id.btn_Update);
        Button Done = findViewById(R.id.btn_Done);

        //view address
        String currentUser = FirebaseAuth.getInstance().getCurrentUser().getUid();
        DocumentReference docRef = FirebaseFirestore.getInstance().collection("Delivery").document(currentUser);
        docRef.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                document = task.getResult();
                if (document.exists()) {
                    Log.d(TAG, "DocumentSnapshot data: " + Objects.requireNonNull(document.getData()));

                    txtName=document.getData().get("Name").toString();
                    txtAddress= document.getData().get("Address").toString();
                }else {
                    Log.d(TAG, "No such document");
                }
            } else {
                Log.d(TAG, "get failed with ", task.getException());
            }
        });


        Edit_name.setText("Kalana",TextView.BufferType.EDITABLE);
        Edit_address.setText("18,Nugegoda", TextView.BufferType.EDITABLE);

        //Update address
        Update.setOnClickListener(v->
        {
            String txtName=Edit_name.getText().toString();
            String txtAddress=Edit_address.getText().toString();
            final HashMap<String, Object> DeliveryMap = new HashMap<>();
            DeliveryMap.put("Name",txtName);
            DeliveryMap.put("Address", txtAddress);

            FirebaseFirestore db = FirebaseFirestore.getInstance();

            db.collection("Delivery").document( currentUser)
                    .set(DeliveryMap)
                    .addOnSuccessListener(documentReference -> {
                        Toast.makeText(this, "Delivery added successfully", Toast.LENGTH_LONG).show();

                    })
                    .addOnFailureListener(e -> Log.w(TAG, "Error adding document", e));

        });

        Update.setOnClickListener(v->
        {
            Intent intent = new Intent( getApplicationContext(),HomeActivity.class);
            startActivity(intent);
        });

    }
    


}