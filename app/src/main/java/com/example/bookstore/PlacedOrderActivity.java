package com.example.bookstore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class PlacedOrderActivity extends AppCompatActivity {
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_placed_order);

        btn=findViewById(R.id.toHomebtn);

        btn.setOnClickListener(v->{
            Intent intent1 = new Intent( getApplicationContext(),HomeActivity.class);
            startActivity(intent1);
        });
    }
}