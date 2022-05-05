package com.example.bookstore;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.HashMap;
import java.util.Objects;

public class BookActivity extends AppCompatActivity {
    private static final String TAG = "EmailPassword";
    DocumentSnapshot document;
    String bookId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.activity_book);

//
        Intent intent = getIntent();
        bookId = intent.getStringExtra("bookId");
        TextView bookTitle = findViewById(R.id.bookTitle);
        TextView bookAuthor = findViewById(R.id.bookAuthor);
        TextView bookPrice = findViewById(R.id.bookPrice);
        TextView bookRating = findViewById(R.id.bookRating);
        TextView bookDescription = findViewById(R.id.bookDescription);

        DocumentReference docRef = FirebaseFirestore.getInstance().collection("Books").document(bookId);
        docRef.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                document = task.getResult();
                if (document.exists()) {
                    Log.d(TAG, "DocumentSnapshot data: " + Objects.requireNonNull(document.getData()));

                    bookTitle.setText(Objects.requireNonNull(document.getData().get("Title")).toString());
                    bookAuthor.setText(Objects.requireNonNull(document.getData().get("Author")).toString());
                    bookPrice.setText(Objects.requireNonNull(document.getData().get("Price")) + "0$");
                    bookRating.setText(Objects.requireNonNull(document.getData().get("Ratings")).toString());
                    bookDescription.setText(Objects.requireNonNull(document.getData().get("Description")).toString());


                    setCover(Objects.requireNonNull(document.getData().get("Image")).toString());
                } else {
                    Log.d(TAG, "No such document");
                }
            } else {
                Log.d(TAG, "get failed with ", task.getException());
            }
        });


        Button RentBtn = findViewById(R.id.RentBtn);
        RentBtn.setOnClickListener(v -> finish());
        ImageButton back = findViewById(R.id.backToBookList);
        back.setOnClickListener(v -> finish());


        Button addToCartBtn = findViewById(R.id.addToCartBtn);
        addToCartBtn.setOnClickListener(view -> addToCart());


    }

    private void addToCart() {

        final HashMap<String, Object> cartMap = new HashMap<>();
        cartMap.put("Author", Objects.requireNonNull(document.getData().get("Author")).toString());
        cartMap.put("Title", Objects.requireNonNull(document.getData().get("Title")).toString());
        cartMap.put("BookId", bookId);
        cartMap.put("Image", Objects.requireNonNull(document.getData().get("Image")).toString());
        cartMap.put("price", Objects.requireNonNull(document.getData().get("Price")));

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("Cart").document("WJhcfXZpxSYXqSQqR2ymcpY7fpP2").collection("Book")
                .add(cartMap)
                .addOnSuccessListener(documentReference -> Log.d(TAG, "DocumentSnapshot written with ID: " + documentReference.getId()))
                .addOnFailureListener(e -> Log.w(TAG, "Error adding document", e));

    }

    public void setCover(String image) {
        // Reference to an image file in Cloud Storage
        StorageReference storageReference = FirebaseStorage.getInstance().getReference().child("Books/" + image);
        // ImageView in your Activity
        ImageView imageView = findViewById(R.id.bookCover);
        // [END storage_load_with_glide]
        storageReference.getBytes(1024 * 1024).addOnSuccessListener(bytes -> {
            Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
            imageView.setImageBitmap(bitmap);
            // Data for "images/island.jpg" is returns, use this as needed
        }).addOnFailureListener(exception -> {
            // Handle any errors
        });
    }


}