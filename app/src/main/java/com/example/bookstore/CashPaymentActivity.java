package com.example.bookstore;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;

public class CashPaymentActivity extends AppCompatActivity {

    private static final String TAG = "Name";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cash_payment);


        final HashMap<String, Object> paymentMap = new HashMap<>();

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        String currentUser = FirebaseAuth.getInstance().getCurrentUser().getUid();
        db.collection("Payments").document("okrsdyqb5VwJNnCgyRgG").collection(currentUser)
                .add(paymentMap)
                .addOnSuccessListener(documentReference -> {
                    Toast.makeText(this, "Verify your Payment", Toast.LENGTH_LONG).show();
                    Intent intent1= new Intent( getApplicationContext(),ThanksActivity.class);
                    startActivity(intent1);
                })
                .addOnFailureListener(e -> Log.w(TAG, "Can't get payment", e));
    }
}