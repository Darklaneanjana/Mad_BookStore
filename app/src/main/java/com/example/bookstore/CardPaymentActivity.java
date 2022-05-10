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
import java.util.HashMap;

public class CardPaymentActivity extends AppCompatActivity {

    private static final String TAG = "Name";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_payment);

        EditText CardNum = findViewById(R.id.cardNum);
        EditText Year= findViewById(R.id.year);
        EditText Month = findViewById(R.id.month);
        EditText CVC= findViewById(R.id.cvc);

        Button send = findViewById(R.id.comfirmBtn);
        

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txtCardNum=CardNum.getText().toString();
                String txtCvc =CVC.getText().toString();
                String txtYear= Year.getText().toString();
                String txtMonth= Month.getText().toString();

                if(TextUtils.isEmpty(txtCardNum ) || TextUtils.isEmpty(txtYear) || TextUtils.isEmpty(txtMonth) ||  TextUtils.isEmpty(txtCvc)){
                    Toast.makeText(CardPaymentActivity.this, "Empty credentials!", Toast.LENGTH_SHORT).show();
                }
                else if(txtCardNum.length()<16 || txtCardNum.length()>16 ){
                    Toast.makeText(CardPaymentActivity.this, "Invalid Card Number!!", Toast.LENGTH_SHORT).show();
                }
                else if(txtYear.length()<4 || txtYear.length()>4 ){
                    Toast.makeText(CardPaymentActivity.this, "Invalid Year!!", Toast.LENGTH_SHORT).show();
                }
                else if(txtMonth.length()<2 || txtMonth.length()>16 ){
                    Toast.makeText(CardPaymentActivity.this, "Invalid Month!!", Toast.LENGTH_SHORT).show();
                }
                else if(txtCvc.length()<3 || txtCvc.length()>3 ){
                    Toast.makeText(CardPaymentActivity.this, "Invalid CVC!!", Toast.LENGTH_SHORT).show();
                }
                else{
                    Button btn_send = findViewById(R.id.comfirmBtn);
                    btn_send.setOnClickListener(view-> addPayment(txtCardNum,txtYear,txtMonth,txtCvc));
                }
            }
        });
    }
    private void addPayment(String txtCardNum, String txtYear,String txtMonth, String txtCvc) {
        final HashMap<String, Object> paymentMap = new HashMap<>();
        paymentMap.put("CardNumber", txtCardNum);
        paymentMap.put("Year", txtYear);
        paymentMap.put("Month", txtMonth);
        paymentMap.put("CVC",txtCvc);
        

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        String currentUser = FirebaseAuth.getInstance().getCurrentUser().getUid();
        db.collection("Payments").document(currentUser)
                .set(paymentMap)
                .addOnSuccessListener(documentReference -> {

                    Toast.makeText(this, "Verify your Payment", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent( getApplicationContext(),VerificationActivity.class);
                    startActivity(intent);
                })
                .addOnFailureListener(e -> Log.w(TAG, "Can't get payment", e));
    }
}