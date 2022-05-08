package com.example.bookstore;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.text.HtmlCompat;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class AboutUsActivity extends AppCompatActivity {

    Button btCall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        btCall = findViewById(R.id.toCallBtn);

        TextView TextEmail = findViewById(R.id.textViewEmail);
        TextEmail.setText(HtmlCompat.fromHtml("<a href='mailto:ask@me.it'>Support@bookbank.lk</a>",HtmlCompat.FROM_HTML_MODE_LEGACY));
        TextEmail.setMovementMethod(LinkMovementMethod.getInstance());

        btCall.setOnClickListener(v -> {
            String Number = "+94771357144";

            btCall.setOnClickListener(view -> {
                Intent intent=new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse(Number));
                startActivity(intent);
            });
        });


    }
}

