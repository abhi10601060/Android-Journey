package com.example.readit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class BookLayout extends AppCompatActivity {
    private Button alreadybtn,btnfav,btnwishlist,btnstart;
    private ImageView image;
    private TextView name, pages, author,short_description,long_description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_layout);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        viewinit();

        Intent intent = getIntent();
        if(intent!=null){
            int bookId = intent.getIntExtra("bookId",-1);
            if(bookId !=-1){
                Book incomingBook = Utils.getInstance(this).getBookById(bookId);
                if(incomingBook!=null){
                    setView(incomingBook);
                    handleAlreadyReadBook(incomingBook);
                    handleFavBook(incomingBook);
                    handleWishList(incomingBook);
                    handleCur(incomingBook);

                }

            }
        }

    }

    private void viewinit(){
        name=findViewById(R.id.book_name_lt);
        pages=findViewById(R.id.pages_lt);
        author=findViewById(R.id.author_lt);
        short_description=findViewById(R.id.shorDescription_lt);
        long_description=findViewById(R.id.longDescription_lt);

        image=findViewById(R.id.image_lt);

        alreadybtn=findViewById(R.id.btn_already_lt);
        btnfav=findViewById(R.id.btn_fav_lt);
        btnstart=findViewById(R.id.btn_start_lt);
        btnwishlist=findViewById(R.id.btn_wishlist_lt);

    }
    private void setView(Book book){
        name.setText(book.getName());
        pages.setText(""+book.getPages());
        author.setText(book.getAuthor());
        Glide.with(this)
                .asBitmap()
                .load(book.getImageurl())
                .into(image);
        short_description.setText(book.getShortDesc());
        long_description.setText(book.getLongDesc());
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

    private void handleAlreadyReadBook(Book book){
        ArrayList<Book> alreadyReadBooks = Utils.getInstance(this).getAlreadyReadBooks();
        boolean alreadyExist=false;
        for(Book b : alreadyReadBooks){
            if(book.getId()==b.getId()){
                alreadyExist=true;
            }
        }

        if(alreadyExist){
            alreadybtn.setEnabled(false);
        }
        else{
            alreadybtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(Utils.getInstance(BookLayout.this).addToAlreadyRead(book)){
                        Toast.makeText(BookLayout.this, "You have already read this book..", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(BookLayout.this,AlreadyReadBooksActivity.class);
                        startActivity(intent);
                    }
                    else {
                        Toast.makeText(BookLayout.this, "Something went Wrong ...Try Again...", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void handleFavBook(Book book){
        ArrayList<Book> favBooks=Utils.getInstance(BookLayout.this).getFavBooks();
        boolean bookExist = false;

        for(Book b : favBooks){
            if(book.getId()==b.getId()){
                bookExist=true;
            }
        }

        if(bookExist==true){
            btnfav.setEnabled(false);
        }
        else {
            btnfav.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(Utils.getInstance(BookLayout.this).addToFav(book)){
                        Toast.makeText(BookLayout.this, "Added to favourites", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(BookLayout.this,FavouritesActivity.class);
                        startActivity(intent);
                    }
                    else {
                        Toast.makeText(BookLayout.this, "Something Went Wrong ", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void handleWishList(Book book){
        ArrayList<Book> wishlist =Utils.getInstance(BookLayout.this).getWishlistBooks();
        boolean bookExist = false;

        for(Book b : wishlist){
            if(b.getId()==book.getId()){
                bookExist=true;
            }
        }
        if(bookExist){
            btnwishlist.setEnabled(false);
        }
        else{
            btnwishlist.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(Utils.getInstance(BookLayout.this).addToWishList(book)){
                        Toast.makeText(BookLayout.this, "Added to Your WishList", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(BookLayout.this, WishListActivity.class);
                        startActivity(intent);
                    }
                    else {
                        Toast.makeText(BookLayout.this, "Something went Wrong", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private  void handleCur(Book book){
        ArrayList<Book> curBooks =Utils.getInstance(this).getCurBooks();

        btnstart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean bookExist = false;
                for(Book b : curBooks){
                    if(book.getId()==b.getId()){
                        bookExist=true;
                    }
                }
                if(bookExist){
                    Intent intent = new Intent(BookLayout.this,ActualBookActivity.class);
                    intent.putExtra("BookId",book.getId());
                    startActivity(intent);
                }
                else {
                    if (Utils.getInstance(BookLayout.this).addToCur(book)){
                        Toast.makeText(BookLayout.this, "Book Added", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(BookLayout.this,ActualBookActivity.class);
                        intent.putExtra("BookId",book.getId());
                        startActivity(intent);
                    }
                    else {
                        Toast.makeText(BookLayout.this, "Something Went Wrong", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });



    }
}