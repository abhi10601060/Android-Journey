/*
 * Copyright (c) 2022. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.example.readit;

import java.util.ArrayList;

public class Utils {

    private ArrayList<Book> allBooks;
    private ArrayList<Book> wishlistBooks;
    private ArrayList<Book> alreadyReadBooks;
    private ArrayList<Book> favBooks;
    private ArrayList<Book> curBooks;


    private static Utils instance;

    private  Utils() {
        if(allBooks==null){
            allBooks=new ArrayList<>();
            initData();
        }
        if(alreadyReadBooks==null){
            alreadyReadBooks=new ArrayList<>();
        }
        if (wishlistBooks==null){
            wishlistBooks=new ArrayList<>();
        }
        if (favBooks==null){
            favBooks=new ArrayList<>();
        }
        if(curBooks==null){
            curBooks=new ArrayList<>();
        }
    }

    private void initData() {
        allBooks.add( new Book(500,"Kakashi", "Abhi hhh", 500,"https://s4.anilist" +
                ".co/file/anilistcdn/character/large/b85-mkVBh2yjxjmx.png","Hello" +
                " this is a description of the book herw we are dhfjv","vavwdquc dvqwbhjd hjbcbq cuiqbdiqbw"));
    }

    public static Utils getInstance(){
        if(instance==null){
            instance = new Utils();
        }
        return instance;
    }

    public ArrayList<Book> getAllBooks() {
        return allBooks;
    }

    public ArrayList<Book> getWishlistBooks() {
        return wishlistBooks;
    }

    public ArrayList<Book> getAlreadyReadBooks() {
        return alreadyReadBooks;
    }

    public ArrayList<Book> getFavBooks() {
        return favBooks;
    }

    public ArrayList<Book> getCurBooks() {
        return curBooks;
    }


    public Book getBookById(int id){
        for(Book b : allBooks){
            if(b.getId()==id){
                return b;
            }
        }
        return null;
    }

    public boolean addToAlreadyRead(Book book){
        return this.alreadyReadBooks.add(book);
    }

    public boolean addToFav(Book book){
        return this.favBooks.add(book);
    }

    public  boolean addToWishList(Book book){
        return this.wishlistBooks.add(book);
    }

    public boolean addToCur(Book book){return this.curBooks.add(book);}

    public boolean removeFromAlreadyRead(Book book){
        return this.alreadyReadBooks.remove(book);
    }

    public boolean removeFromFav(Book book){
        return this.favBooks.remove(book);
    }

    public boolean removeFromWishList(Book book){
        return this.wishlistBooks.remove(book);
    }

    public boolean removeFromCur(Book book){return this.curBooks.remove(book);}

}
