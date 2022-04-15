package com.example.readit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;

import java.util.ArrayList;

public class AllBookActivity extends AppCompatActivity {
    private RecyclerView allBookRV;
    private String id = "allBooks";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_book);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

//        ArrayList<Book> books = new ArrayList<>();
//        books.add(new Book(500,"Kakashi", "Abhi hhh", 500,"https://s4.anilist" +
//                ".co/file/anilistcdn/character/large/b85-mkVBh2yjxjmx.png","Hello" +
//                " this is a description of the book herw we are dhfjv","vavwdquc dvqwbhjd hjbcbq cuiqbdiqbw"));


        allBookRV=findViewById(R.id.allBookRecyclerView);
        BookAdapter adapter = new BookAdapter(this, id);

        adapter.setBooks(Utils.getInstance(this).getAllBooks());

        allBookRV.setAdapter(adapter);
        allBookRV.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));

    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}