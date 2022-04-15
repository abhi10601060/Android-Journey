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
        books.add( new Book(500,"Kakashi", "Abhi hhh", 500,"https://s4.anilist" +
                ".co/file/anilistcdn/character/large/b85-mkVBh2yjxjmx.png","Hello" +
                " this is a description of the book herw we are dhfjv","vavwdquc dvqwbhjd hjbcbq cuiqbdiqbw"));

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
