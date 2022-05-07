package com.example.bookstore;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;

public class FeedbackActivity extends AppCompatActivity {
    private static final String TAG = "Name";
    private EditText name, email, message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        message = findViewById(R.id.message);

        Button send = findViewById(R.id.btn_send);
        //Firebase.setAndroidContext(this);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txtName = name.getText().toString();
                String txtEmail = email.getText().toString();
                String txtMessage = message.getText().toString();

                Button btn_send = findViewById(R.id.btn_send);
                btn_send.setOnClickListener(view -> addToFeedback(txtName, txtEmail, txtMessage));
            }

        });
    }

    private void addToFeedback(String txtName, String txtEmail, String txtMessage) {

        final HashMap<String, Object> feedbackMap = new HashMap<>();
        feedbackMap.put("Name", txtName);
        feedbackMap.put("Email", txtEmail);
        feedbackMap.put("Message", txtMessage);

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("Feedbacks").document("kRy8xpkMtZucDXZMmKIE").collection("Name")
                .add(feedbackMap)
                .addOnSuccessListener(documentReference -> {
                    Log.d(TAG, "DocumentSnapshot written with ID: " + documentReference.getId());
                    Toast.makeText(this, "Feedback added successfully", Toast.LENGTH_LONG).show();
                })
                .addOnFailureListener(e -> Log.w(TAG, "Error adding document", e));

    }

}