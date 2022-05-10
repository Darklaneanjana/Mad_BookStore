package com.example.bookstore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class CardPaymentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_payment);

        Button send = findViewById(R.id.comfirmBtn);

        send.setOnClickListener(v->{
            Intent intent = new Intent( getApplicationContext(),VerificationActivity.class);
            startActivity(intent);
        });
    }
}