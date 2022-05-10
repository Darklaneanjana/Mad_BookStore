package com.example.bookstore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

public class SelectPaymentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_payment);

        ImageButton send1 = findViewById(R.id.cardBtn);
        ImageButton send2 = findViewById(R.id.cashBtn);

        send1.setOnClickListener(v->{
            Intent intent1 = new Intent( getApplicationContext(),CardPaymentActivity.class);
            startActivity(intent1);
        });

        send2.setOnClickListener(v->{
            Intent intent2 = new Intent( getApplicationContext(),CashPaymentActivity.class);
            startActivity(intent2);
        });


    }
}