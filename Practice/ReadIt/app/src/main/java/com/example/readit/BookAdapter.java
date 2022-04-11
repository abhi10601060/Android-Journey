package com.example.readit;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.ViewHolder> {
    private ArrayList<Book> books = new ArrayList<>();
    private Context context;

    public void setBooks(ArrayList<Book> books) {
        this.books = books;
    }

    public BookAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public BookAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.book_layout,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull BookAdapter.ViewHolder holder, int position) {
        holder.bkName.setText(books.get(position).getName());
        holder.author.setText("Written By :"+ books.get(position).getAuthor());
        holder.pages.setText("pages : "+ books.get(position).getPages());
        holder.shortDesc.setText("Description : " + books.get(position).getShortDesc());

        Glide.with(context)
                .load(books.get(position).getImageurl())
                .into(holder.image);


    }

    @Override
    public int getItemCount() {
        return books.size();
    }




    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView bkName , author , pages, shortDesc;
        private ImageView image;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            bkName=itemView.findViewById(R.id.book_name_txt);
            author =itemView.findViewById(R.id.author_name_txt);
            pages =itemView.findViewById(R.id.pages_text);
            shortDesc=itemView.findViewById(R.id.short_description_txt);
            image=itemView.findViewById(R.id.book_image);

        }
    }

}
