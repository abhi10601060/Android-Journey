package com.example.readit;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.text.method.TextKeyListener;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.ViewHolder> {
    private ArrayList<Book> books = new ArrayList<>();
    private Context context;
    private  String parentId;

    public void setBooks(ArrayList<Book> books) {
        this.books = books;
    }

    public BookAdapter(Context context , String id) {
        this.context = context;
        this.parentId=id;
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
        holder.author.setText(books.get(position).getAuthor());
        holder.shortDesc.setText("Description : " + books.get(position).getShortDesc());

        Glide.with(context)
                .load(books.get(position).getImageurl())
                .into(holder.image);


        holder.downArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TransitionManager.beginDelayedTransition(holder.parent);
                holder.downArrow.setVisibility(View.GONE);
                holder.expandedLayout.setVisibility(View.VISIBLE);


                if(parentId.equals("allBooks")){
                    holder.delete.setVisibility(View.GONE);
                }
                else if(parentId.equals("alreadyReadBooks")){
                    holder.delete.setVisibility(View.VISIBLE);

                    holder.delete.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(context);
                            builder.setMessage("Are you sure you want to delete "+books.get(holder.getAdapterPosition()).getName());
                            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    if(Utils.getInstance().removeFromAlreadyRead(books.get(holder.getAdapterPosition()))){
                                        Toast.makeText(context, "Book Removed", Toast.LENGTH_SHORT).show();
                                        notifyDataSetChanged();
                                    }
                                    else{
                                        Toast.makeText(context, "Something Went Wrong", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                            builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {

                                }
                            });
                            builder.create().show();

                        }
                    });
                }
                else if(parentId.equals("FavouriteBooks")){
                    holder.delete.setVisibility(View.VISIBLE);

                    holder.delete.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            AlertDialog.Builder builder=new AlertDialog.Builder(context);
                            builder.setMessage("Are you sure you want to delete "+books.get(holder.getAdapterPosition()).getName());
                            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    if(Utils.getInstance().removeFromFav(books.get(holder.getAdapterPosition()))){
                                        Toast.makeText(context, "Book Removed", Toast.LENGTH_SHORT).show();
                                        notifyDataSetChanged();
                                    }
                                    else {
                                        Toast.makeText(context, "Something Went Wrong", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {

                                }
                            });
                            builder.create().show();
                        }
                    });

                }
                else if (parentId.equals("Wishlist")){
                    holder.delete.setVisibility(View.VISIBLE);

                    holder.delete.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(context);
                            builder.setMessage("Are you sure you want to delete "+books.get(holder.getAdapterPosition()).getName());
                            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    if(Utils.getInstance().removeFromWishList(books.get(holder.getAdapterPosition()))){
                                        Toast.makeText(context, "Book Removed", Toast.LENGTH_SHORT).show();
                                        notifyDataSetChanged();
                                    }
                                    else{
                                        Toast.makeText(context, "Something Went Wrong", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {

                                }
                            });
                            builder.create().show();
                        }
                    });
                }
                else if (parentId.equals("CurReading")){
                    holder.delete.setVisibility(View.VISIBLE);

                    holder.delete.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            AlertDialog.Builder builder= new AlertDialog.Builder(context);
                            builder.setMessage("Are you sure you want to delete "+books.get(holder.getAdapterPosition()).getName());
                            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    if(Utils.getInstance().removeFromCur(books.get(holder.getAdapterPosition()))){
                                        Toast.makeText(context, "Book Removed", Toast.LENGTH_SHORT).show();
                                        notifyDataSetChanged();
                                    }
                                    else {
                                        Toast.makeText(context, "Something Went Wrong", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {

                                }
                            });
                            builder.create().show();
                        }
                    });
                }
            }
        });

        holder.upArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TransitionManager.beginDelayedTransition(holder.parent);
                holder.downArrow.setVisibility(View.VISIBLE);
                holder.expandedLayout.setVisibility(View.GONE);
            }
        });

        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context, BookLayout.class);
                intent.putExtra("bookId",books.get(holder.getAdapterPosition()).getId());
                context.startActivity(intent);

            }
        });


    }

    @Override
    public int getItemCount() {
        return books.size();
    }




    public class ViewHolder extends RecyclerView.ViewHolder{

        private CardView parent;

        private TextView bkName , author , shortDesc , delete;
        private ImageView image , downArrow, upArrow;

        private RelativeLayout expandedLayout;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            bkName=itemView.findViewById(R.id.book_name_txt);
            author =itemView.findViewById(R.id.author_name);
            shortDesc=itemView.findViewById(R.id.short_description_txt);
            image=itemView.findViewById(R.id.book_image);
            downArrow = itemView.findViewById(R.id.downArrowImg);
            upArrow=itemView.findViewById(R.id.upArrowImg);

            expandedLayout=itemView.findViewById(R.id.expandedRelstiveLayout);

            parent= itemView.findViewById(R.id.book_card);

            delete =itemView.findViewById(R.id.delete_btn);

        }
    }

}
