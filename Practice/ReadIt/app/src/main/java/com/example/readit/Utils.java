/*
 * Copyright (c) 2022. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.example.readit;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class Utils {

    private SharedPreferences sharedPreferences;

    private static final String ALL_BOOKS_KEY= "all_books";
    private static final String ALREADY_READ_BOOKS = "alredy_read";
    private static final String WISHLIST = "wishlist_books";
    private static  final String FAV_BOOKS = "fav_books";
    private static final String CUR_BOOKS = "cur_books";


    private ArrayList<Book> allBooks;
    private ArrayList<Book> wishlistBooks;
    private ArrayList<Book> alreadyReadBooks;
    private ArrayList<Book> favBooks;
    private ArrayList<Book> curBooks;


    private static Utils instance;

    private  Utils(Context context) {
        sharedPreferences = context.getSharedPreferences("alternateDB",Context.MODE_PRIVATE);
        Gson gson=new Gson();
        SharedPreferences.Editor editor=sharedPreferences.edit();

        if(getAllBooks()==null){
            initData();
        }
        if(getAlreadyReadBooks()==null){
            ArrayList<Book> books = new ArrayList<>();
            editor.putString(ALREADY_READ_BOOKS,gson.toJson(books));
            editor.commit();
        }
        if (getWishlistBooks()==null){
            editor.putString(WISHLIST,gson.toJson(new ArrayList<Book>()));
            editor.commit();

        }
        if (getFavBooks()==null){
            editor.putString(FAV_BOOKS,gson.toJson(new ArrayList<Book>()));
            editor.commit();
        }
        if(getCurBooks()==null){
            editor.putString(CUR_BOOKS,gson.toJson(new ArrayList<Book>()));
            editor.commit();
        }
    }

    private void initData() {
        ArrayList<Book> books =new ArrayList<>();
        books.add( new Book(1,"Venom (2018)#1", "Donny Cates", 170,
                "https://i.annihil.us/u/prod/marvel/i/mg/8/a0/5aea3123880ca/clean.jpg",
                "n the wake of S.H.I.E.L.D.'s collapse, an ancient and primordial evil has been awakened",
                "In the wake of S.H.I.E.L.D.'s collapse, an ancient and primordial evil has been awakened beneath the streets of New York, and with it," +
                        " something equally evil has awakened in that most Wicked of Webslingers - VENOM! Still a Lethal Protector of the innocents in New York, this never-before-seen threat could force Venom to relinquish everything it holds dear - " +
                        "including Eddie Brock! Join two of the hottest creators in comics today, Donny Cates and Ryan Stegman, for a VENOM adventure a thousand years in the making!"));
        books.add(new Book(2,"Amazing Fantasy","Stan Lee",145,
        "https://i.annihil.us/u/prod/marvel/i/mg/f/10/598363848588e/detail.jpg",
        "Jack Kirby and Steve Ditko collaborated on this cover to create what",
                "Jack Kirby and Steve Ditko collaborated on this cover to create what is quite po" +
                        "ssibly the most iconic image in Marvel Comics' history. Before all the clones, symbiotes and civil " +
                        "wars we see Spider-Man in a simpler time doing what he" +
                        " does best, catching crooks and saving the day. "));
        books.add(new Book(3,"Strangert Things","Jeff Lemire",237,
                "https://static.wikia.nocookie.net/strangerthings8338/images/4/43/Into_Fire4.jpg",
                "Tales from two diverse worlds!",
                "The greatest Super Heroes in the DC universe, the Justice " +
                        "League, answer mail from their biggest fans--kids! Courtesy of Michael Nort" +
                        "hrop, the New York Times bestselling author of TombQuest, and artist Gustavo Duarte, DC's iconic Super Heroes find time to answer questions both big and small when the" +
                        "y are not busy saving the world. Their honest and humorous responses will surprise and delight readers of any age, as it turns out " +
                        "that being a superhero is not too different from being a kid."));
        books.add(new Book(4,"Attack On titan","Hajime Isayama",457,
                "https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1460350850l/29874565._SY475_.jpg",
                "THE MANGA HIT OF THE DECADE! ",
               "Read the series that's dominating graphic novel best" +
                       "seller lists! A century ago, the grotesque giants known as Titans appeared and consu" +
                       "med all but a few thousand humans. The survivors took refuge behind giant walls. Today, the threat of the Titans is a distant memory, and a boy named Eren yearns to e" +
                       "xplore the world beyond Wall Maria. But what began as a childish dream will become an all-too-real nightmare when the Titans return " +
                       "and humanity is once again on the brink of extinction..." ));

        books.add(new Book(5,"Berserk Volume #1", " Kentaro Miura",368,
                "https://images-na.ssl-images-amazon.com/images/I/91D07epNE9L.jpg",
                "the extreme - violent, horrifying, mercilessly and the wellspring for the internationally popular anime series.",
                "Created by Kentaro Miura, Berserk is manga mayhem to the extreme - violent, horrifying, and mercilessly funny - and the wellspring for the internationally popular anime series." +
                        " Not for the squeamish or the easily offended, Berserk asks for no quarter - and offers none!\n" +
                        "His name is Guts, the Black Swordsman, a feared warrior spoken of o" +
                        "nly in whispers. Bearer of a gigantic sword, an iron hand, and the scars of cou" +
                        "ntless battles and tortures, his flesh is also indelibly marked with The Brand, an unholy symbol t" +
                        "hat draws the forces of darkness to him and dooms him as their sacrifice. But Guts won't take his fate" +
                        " lying down; he'll cut a crimson swath of carnage through the ranks of the damned - and anyone else foolish enough to oppose him! Accompanied by Puck the Elf, more an annoyance than a companion, Gut" +
                        "s relentlessly follows a dark, bloodstained path that leads only to death...or vengeance."));


        SharedPreferences.Editor editor= sharedPreferences.edit();
        Gson gson =new Gson();
        String booksString = gson.toJson(books);
        editor.putString(ALL_BOOKS_KEY,booksString);
        editor.commit();
    }

    public static Utils getInstance(Context context){
        if(instance==null){
            instance = new Utils(context);
        }
        return instance;
    }

    public ArrayList<Book> getAllBooks() {
        ArrayList<Book> books = new ArrayList<>();
        Gson gson =new Gson();
        Type type = new TypeToken<ArrayList<Book>>(){}.getType();
        books=gson.fromJson(sharedPreferences.getString(ALL_BOOKS_KEY,null),type);
        return books;
    }

    public ArrayList<Book> getWishlistBooks() {
        ArrayList<Book> books = new ArrayList<>();
        Gson gson = new Gson();
        books = gson.fromJson(sharedPreferences.getString(WISHLIST,null),new TypeToken<ArrayList<Book>>(){}.getType());
        return books;
    }

    public ArrayList<Book> getAlreadyReadBooks() {
        ArrayList<Book> books  =new ArrayList<>();
        Gson gson =new Gson();
        books=gson.fromJson(sharedPreferences.getString(ALREADY_READ_BOOKS,null),new TypeToken<ArrayList<Book>>(){}.getType());
        return books;
    }

    public ArrayList<Book> getFavBooks() {
        ArrayList<Book> books = new ArrayList<>();
        Gson gson = new Gson();
        books= gson.fromJson(sharedPreferences.getString(FAV_BOOKS,null),new TypeToken<ArrayList<Book>>(){}.getType());
        return books;
    }

    public ArrayList<Book> getCurBooks() {
        ArrayList<Book> books= new ArrayList<>();
        Gson gson = new Gson();
        books=gson.fromJson(sharedPreferences.getString(CUR_BOOKS,null),new TypeToken<ArrayList<Book>>(){}.getType());
        return books;
    }


    public Book getBookById(int id){
        ArrayList<Book> books = getAllBooks();
        if(books!=null){
            for(Book b : books){
                if(b.getId()==id){
                    return b;
                }
            }
        }
        return null;
    }

    public boolean addToAlreadyRead(Book book){
        ArrayList<Book> books =getAlreadyReadBooks();
        if(books!=null){
            if(books.add(book)){
                Gson gson=new Gson();
                SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.remove(ALREADY_READ_BOOKS);
                editor.putString(ALREADY_READ_BOOKS,gson.toJson(books));
                editor.commit();
                return true;
            }
        }
        return false;
    }

    public boolean addToFav(Book book){
        ArrayList<Book> books =getFavBooks();
        if(books!=null){
            if(books.add(book)){
                Gson gson=new Gson();
                SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.remove(FAV_BOOKS);
                editor.putString(FAV_BOOKS,gson.toJson(books));
                editor.commit();
                return true;
            }
        }
        return false;
    }

    public  boolean addToWishList(Book book){
        ArrayList<Book> books =getWishlistBooks();
        if(books!=null){
            if(books.add(book)){
                Gson gson=new Gson();
                SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.remove(WISHLIST);
                editor.putString(WISHLIST,gson.toJson(books));
                editor.commit();
                return true;
            }
        }
        return false;
    }

    public boolean addToCur(Book book){
        ArrayList<Book> books =getCurBooks();
        if(books!=null){
            if(books.add(book)){
                Gson gson=new Gson();
                SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.remove(CUR_BOOKS);
                editor.putString(CUR_BOOKS,gson.toJson(books));
                editor.commit();
                return true;
            }
        }
        return false;
    }

    public boolean removeFromAlreadyRead(Book book){
        ArrayList<Book> books =getAlreadyReadBooks();
        if(books!=null){
            for (Book b : books){
                if (b.getId()==book.getId()){
                    if(books.remove(b)){
                        Gson gson=new Gson();
                        SharedPreferences.Editor editor=sharedPreferences.edit();
                        editor.remove(ALREADY_READ_BOOKS);
                        editor.putString(ALREADY_READ_BOOKS,gson.toJson(books));
                        editor.commit();
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean removeFromFav(Book book){
        ArrayList<Book> books =getFavBooks();
        if(books!=null){
            for (Book b : books){
                if (b.getId()==book.getId()){
                    if(books.remove(b)){
                        Gson gson=new Gson();
                        SharedPreferences.Editor editor=sharedPreferences.edit();
                        editor.remove(FAV_BOOKS);
                        editor.putString(FAV_BOOKS,gson.toJson(books));
                        editor.commit();
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean removeFromWishList(Book book){
        ArrayList<Book> books =getWishlistBooks();
        if(books!=null){
            for (Book b : books){
                if (b.getId()==book.getId()){
                    if(books.remove(b)){
                        Gson gson=new Gson();
                        SharedPreferences.Editor editor=sharedPreferences.edit();
                        editor.remove(WISHLIST);
                        editor.putString(WISHLIST,gson.toJson(books));
                        editor.commit();
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean removeFromCur(Book book){
        ArrayList<Book> books =getCurBooks();
        if(books!=null){
            for (Book b : books){
                if (b.getId()==book.getId()){
                    if(books.remove(b)){
                        Gson gson=new Gson();
                        SharedPreferences.Editor editor=sharedPreferences.edit();
                        editor.remove(CUR_BOOKS);
                        editor.putString(CUR_BOOKS,gson.toJson(books));
                        editor.commit();
                        return true;
                    }
                }
            }
        }
        return false;
    }

}
