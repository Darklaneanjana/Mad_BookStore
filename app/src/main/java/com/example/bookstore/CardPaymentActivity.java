package com.example.bookstore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class CardPaymentActivity extends AppCompatActivity {

    private static final String TAG = "Name";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_payment);

        EditText CardNum = findViewById(R.id.cardNum);
        EditText Expiry=(EditText) findViewById(R.id.editTextDate);
        EditText CVC= findViewById(R.id.cvc);

        Button send = findViewById(R.id.comfirmBtn);
        

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String txtCardNum=CardNum.getText().toString();
                String txtCvc =CVC.getText().toString();
                SimpleDateFormat sdf = new SimpleDateFormat( "yyyy/mm" );
                String txtExpiry= sdf.format(Expiry.getText().toString());

                if(TextUtils.isEmpty(txtCardNum ) || TextUtils.isEmpty(txtExpiry) ||  TextUtils.isEmpty(txtCvc)){
                    Toast.makeText(CardPaymentActivity.this, "Empty credentials!", Toast.LENGTH_SHORT).show();
                }
                else if(txtCardNum.length()<16 ){
                    Toast.makeText(CardPaymentActivity.this, "Invalid Card Number!!", Toast.LENGTH_SHORT).show();
                }else if(txtCvc.length()<3 ){
                    Toast.makeText(CardPaymentActivity.this, "Invalid CVC!!", Toast.LENGTH_SHORT).show();
                }
                else{
                    addPayment(txtCardNum,txtExpiry,txtCvc);
                }
            }
        });
    }
    private void addPayment(String txtCardNum, String txtExpiry, String txtCvc) {
        final HashMap<String, Object> paymentMap = new HashMap<>();
        paymentMap.put("CardNumber", txtCardNum);
        paymentMap.put("Expiry", txtExpiry);
        paymentMap.put("CVC",txtCvc);
        

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        String currentUser = FirebaseAuth.getInstance().getCurrentUser().getUid();
        db.collection("Payments").document(currentUser)
                .set(paymentMap)
                .addOnSuccessListener(documentReference -> {

                    Toast.makeText(this, "Verify your Payment", Toast.LENGTH_LONG).show();
                })
                .addOnFailureListener(e -> Log.w(TAG, "Can't get payment", e));
    }
}