package com.example.bookstore.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.bookstore.BookActivity;
import com.example.bookstore.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
//        HomeViewModel homeViewModel =
//                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        Button textView1 = binding.bookOneBtn;
        textView1.setOnClickListener(v -> {
            String bookId = "Lfuk2Z5AJxUExtIQlUoL";
            Intent intent = new Intent(getContext(), BookActivity.class);
            intent.putExtra("bookId", bookId);
            startActivity(intent);
        });



        Button textView2 = binding.bookTwoBtn;
        textView2.setOnClickListener(v -> {
            String bookId = "akW6ttqrKvS0ueyGopM2";
            Intent intent = new Intent(getContext(), BookActivity.class);
            intent.putExtra("bookId", bookId);
            startActivity(intent);
        });
        Button textView3 = binding.bookThreeBtn;
        textView3.setOnClickListener(v -> {
            String bookId = "e2NkoHEWJe1lRn81myQn";
            Intent intent = new Intent(getContext(), BookActivity.class);
            intent.putExtra("bookId", bookId);
            startActivity(intent);
        });
        Button textView4 = binding.bookFourBtn;
        textView4.setOnClickListener(v -> {
            String bookId = "ksuO00nW4dQd6ZwfvRv3";
            Intent intent = new Intent(getContext(), BookActivity.class);
            intent.putExtra("bookId", bookId);
            startActivity(intent);
        });
        Button textView5 = binding.bookFiveBtn;
        textView5.setOnClickListener(v -> {
            String bookId = "rkZ2oz9RFUTqulj9x7P3";
            Intent intent = new Intent(getContext(), BookActivity.class);
            intent.putExtra("bookId", bookId);
            startActivity(intent);
        });

//        final TextView textView = binding.textHome;
//        homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}