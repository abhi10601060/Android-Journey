package com.example.readit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class AllBookActivity extends AppCompatActivity {
    private RecyclerView allBookRV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_book);



        ArrayList<Book> books = new ArrayList<>();
        books.add(new Book(500,"Kakashi", "Abhi hhh", 500,"https://s4.anilist" +
                ".co/file/anilistcdn/character/large/b85-mkVBh2yjxjmx.png","Hello" +
                " this is a description of the book herw we are dhfjv","vavwdquc dvqwbhjd hjbcbq cuiqbdiqbw"));


        allBookRV=findViewById(R.id.allBookRecyclerView);
        BookAdapter adapter = new BookAdapter(this);

        adapter.setBooks(books);

        allBookRV.setAdapter(adapter);
        allBookRV.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));

    }
}