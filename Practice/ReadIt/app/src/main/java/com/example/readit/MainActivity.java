package com.example.readit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button allBk , curBk ,wishlist , favBk , about , completeBk;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewInit();

        allBk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,AllBookActivity.class);
                startActivity(intent);
            }
        });
        completeBk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(MainActivity.this,AlreadyReadBooksActivity.class);
                startActivity(intent);
            }
        });
        favBk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, FavouritesActivity.class);
                startActivity(intent);
            }
        });
        wishlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,WishListActivity.class );
                startActivity(intent);
            }
        });
    }
    private void viewInit(){
        allBk=findViewById(R.id.btn_allBooks);
        curBk = findViewById(R.id.btn_curBooks);
        wishlist = findViewById(R.id.btn_wishlist);
        favBk = findViewById(R.id.btn_favourite);
        about = findViewById(R.id.btn_about);
        completeBk =findViewById(R.id.btn_completed);
    }
}