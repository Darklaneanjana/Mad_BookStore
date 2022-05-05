package com.example.bookstore;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.bookstore.databinding.ActivityMainBinding;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class HomeActivity extends AppCompatActivity {
    public static String bookId;
    private static final String TAG = "EmailPassword";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Objects.requireNonNull(getSupportActionBar()).hide();

        com.example.bookstore.databinding.ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        Button logout = findViewById(R.id.logout);
        logout.setOnClickListener(v -> {
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(HomeActivity.this, LoginActivity.class));
            finish();
            Toast.makeText(HomeActivity.this, "logout una.",
                    Toast.LENGTH_LONG).show();

        });


        Button toBookOne = findViewById(R.id.bookOneBtn);
        toBookOne.setOnClickListener(v -> {
            String bookId = "Lfuk2Z5AJxUExtIQlUoL";
            Intent intent = new Intent(HomeActivity.this, BookActivity.class);
            intent.putExtra("bookId", bookId);
            startActivity(intent);

        });


        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_cart, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);
    }


}