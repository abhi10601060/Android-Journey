package com.example.readit;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button allBk , curBk ,wishlist , favBk , about , completeBk ;


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
        curBk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(MainActivity.this,CurReadingBooksActivity.class);
                startActivity(intent);
            }
        });
        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setMessage("Design and Developed by Abhi...\n"+
                        "Check my website for more fun Applications");

                builder.setPositiveButton("VISIT", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent =new Intent(MainActivity.this,WebviewActivity.class);
                        intent.putExtra("url","https://github.com/abhi10601060/Android-Journey/tree/master");
                        startActivity(intent);
                    }
                });
                builder.setNegativeButton("DISMISS", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });
                builder.create().show();

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