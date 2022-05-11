package com.example.bookstore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class ThanksActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thanks);

        Button btn = findViewById(R.id.toHomebtn);

        btn.setOnClickListener(v->{
            Intent intent = new Intent(getApplicationContext(),HomeActivity.class);
            startActivity(intent);
        });

    }
}