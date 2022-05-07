package com.example.bookstore;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Objects;

public class FeedbackActivity extends AppCompatActivity {
    private static final String TAG = "Name";
    private EditText  message;
    private TextView viewMessage, viewName;
    DocumentSnapshot document;
    String userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        message = findViewById(R.id.message);

        Button send = findViewById(R.id.btn_send);
        //Firebase.setAndroidContext(this);

        String currentUser = FirebaseAuth.getInstance().getCurrentUser().getUid();

        DocumentReference docRef = FirebaseFirestore.getInstance().collection("Feedbacks").document(currentUser);
        docRef.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                document = task.getResult();
                if (document.exists()) {
                    Log.d(TAG, "DocumentSnapshot data: " + Objects.requireNonNull(document.getData()));
                    viewMessage.setText(Objects.requireNonNull(document.getData().get("Message")).toString());

                } else {
                    Log.d(TAG, "No such document");
                }
            } else {
                Log.d(TAG, "get failed with ", task.getException());
            }
        });

        DocumentReference docRef1 = FirebaseFirestore.getInstance().collection("Users").document(currentUser);
        docRef1.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                document = task.getResult();
                if (document.exists()) {
                    Log.d(TAG, "DocumentSnapshot data: " + Objects.requireNonNull(document.getData()));

                    userName=document.getData().get("Name").toString();
                }else {
                    Log.d(TAG, "No such document");
                }
            } else {
                Log.d(TAG, "get failed with ", task.getException());
            }
        });

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txtName = userName;
                String txtMessage = message.getText().toString();

                Button btn_send = findViewById(R.id.btn_send);
                btn_send.setOnClickListener(view -> addToFeedback(txtName, txtMessage));
            }

        });
    }

    private void addToFeedback(String txtName, String txtMessage) {


        final HashMap<String, Object> feedbackMap = new HashMap<>();
        feedbackMap.put("Name", txtName);
        feedbackMap.put("Message", txtMessage);

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        String currentUser = FirebaseAuth.getInstance().getCurrentUser().getUid();
        db.collection("Feedbacks").document(currentUser)
                .set(feedbackMap)
                .addOnSuccessListener(documentReference -> {

                    Toast.makeText(this, "Feedback added successfully", Toast.LENGTH_LONG).show();
                })
                .addOnFailureListener(e -> Log.w(TAG, "Error adding document", e));

    }

}