package com.example.bookstore;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity {
    private static final String TAG = "EmailPassword";

    RecyclerView recyclerView;
    ArrayList<User> userArrayList;
    MyAdapter myAdapter;
    FirebaseFirestore db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

    recyclerView = findViewById(R.id.recyclerView);
    recyclerView.setHasFixedSize(true);
    recyclerView.setLayoutManager(new LinearLayoutManager(this));

    db = FirebaseFirestore.getInstance();
    userArrayList = new ArrayList<User>();
    myAdapter = new MyAdapter(Main2Activity.this, userArrayList);
    recyclerView.setAdapter(myAdapter);
    EventChangeListner();


    }

    private void EventChangeListner() {
        db.collection("Users").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {

                if(error != null){
                    Log.e("Firestore Error",error.getMessage());
                    return;
                }
                for(DocumentChange dc:value.getDocumentChanges()){
                    if(dc.getType() == DocumentChange.Type.ADDED){
                        userArrayList.add(dc.getDocument().toObject(User.class));
                    }
                    myAdapter.notifyDataSetChanged();
                }
            }
        });
    }

}