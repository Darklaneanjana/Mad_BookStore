package com.example.bookstore.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.bookstore.BookActivity;
import com.example.bookstore.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        Button textView1 = binding.bookOneBtn;
        textView1.setOnClickListener(v -> {
            String bookId = "Lfuk2Z5AJxUExtIQlUoL";
            Intent intent = new Intent(getContext(), BookActivity.class);
            intent.putExtra("bookId", bookId);
            startActivity(intent);
        });

        final TextView textView = binding.textHome;
        homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        return root;
    }

//    nav_host_fragment_activity_main

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}