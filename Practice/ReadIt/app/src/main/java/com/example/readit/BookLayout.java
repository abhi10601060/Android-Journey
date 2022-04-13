package com.example.readit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.w3c.dom.Text;

public class BookLayout extends AppCompatActivity {
    private Button alreadybtn,btnfav,btnwishlist,btnstart;
    private ImageView image;
    private TextView name, pages, author,short_description,long_description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_layout);

        viewinit();

//        Book book = new Book(500,"Kakashi", "Abhi hhh", 500,"https://s4.anilist" +
//                ".co/file/anilistcdn/character/large/b85-mkVBh2yjxjmx.png","Hello" +
//                " this is a description of the book herw we are dhfjv","vavwdquc dvqwbhjd hjbcbq cuiqbdiqbw");

        Intent intent = getIntent();
        if(intent!=null){
            int bookId = intent.getIntExtra("bookId",-1);
            if(bookId !=-1){
                Book incomingBook = Utils.getInstance().getBook(bookId);
                if(incomingBook!=null){
                    setView(incomingBook);
                }

            }
        }


//        setView(book);

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
}