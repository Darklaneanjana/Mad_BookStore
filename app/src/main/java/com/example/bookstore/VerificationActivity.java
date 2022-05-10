package com.example.bookstore;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.text.HtmlCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class VerificationActivity extends AppCompatActivity {

    TextView OTP;
    Button generate_otp;
    EditText mobile_number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_verification);

        OTP = findViewById(R.id.otp);
        generate_otp = findViewById(R.id.generate_otp);
        mobile_number = findViewById(R.id.mobile_number);


        OTP.setText(HtmlCompat.fromHtml(getResources(""),HtmlCompat.FROM_HTML_MODE_LEGACY));
        generate_otp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mobile_number.getText().toString().equals(""))
                    Toast.makeText(getApplicationContext(),"Please enter the mobile no.",Toast.LENGTH_SHORT).show();
                else if(mobile_number.getText().length()<10)
                    Toast.makeText(getApplicationContext(),"Please enter correct mobile no.",Toast.LENGTH_SHORT).show();
                else{
                    Intent intent = new Intent(getApplicationContext(),VerificationComfirmActivity.class);
                    startActivity(intent);
                }

            }
        });
    }
}