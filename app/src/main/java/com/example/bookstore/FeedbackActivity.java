package com.example.bookstore;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
//import com.google.firebase.ktx.Firebase;

import java.util.HashMap;
import java.util.Objects;

public class FeedbackActivity extends AppCompatActivity {
    private static final String TAG = "Name";
    private EditText name,email,message;
    private Button send, details;
    DocumentSnapshot document;
    String feedbackId;
   // private Firebase firestore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        message = findViewById(R.id.message);

        send = findViewById(R.id.btn_send);
        details = findViewById(R.id.btn_details);
        //Firebase.setAndroidContext(this);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txtName = name.getText().toString();
                String txtEmail = email.getText().toString();
                String txtMessage = message.getText().toString();

                Button btn_send = findViewById(R.id.btn_send);
                btn_send.setOnClickListener(view -> addToFeedback());
            }

        });
    }
        private void addToFeedback(){

            final HashMap<String, Object> feedbackMap = new HashMap<>();
            feedbackMap.put("Name",Objects.requireNonNull(document.getData().get("Name")).toString());
            feedbackMap.put("Email",Objects.requireNonNull(document.getData().get("Email")).toString());
            feedbackMap.put("Message",Objects.requireNonNull(document.getData().get("Message")).toString());

            FirebaseFirestore db = FirebaseFirestore.getInstance();
            Task<DocumentReference> documentReferenceTask = db.collection("Feedbacks").document("kRy8xpkMtZucDXZMmKIE").collection("Name")
                    .add(feedbackMap)
                    .addOnSuccessListener(documentReference -> Log.d(TAG, "DocumentSnapshot written with ID: " + documentReference.getId()))
                    .addOnFailureListener(e -> Log.w(TAG, "Error adding document", e));

        }

}