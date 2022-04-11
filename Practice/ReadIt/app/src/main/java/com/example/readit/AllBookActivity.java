package com.example.readit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class AllBookActivity extends AppCompatActivity {
    private RecyclerView allBookRV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_book);

        allBookRV=findViewById(R.id.allBookRecyclerView);
    }
}