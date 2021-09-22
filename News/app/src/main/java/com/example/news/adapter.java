package com.example.news;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class adapter extends RecyclerView.Adapter<UserViewHolder> {

    public ArrayList<data> list;
    public Context mainContext;
    static String des="description";
    public  adapter(ArrayList<data> get, Context main)
    {
        this.list=get;
        this.mainContext=main;
    }
    public adapter()
    {

    }
    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_adapter,parent,false);
        //view holds layout for each item in arrayList
        UserViewHolder viewHolder=new UserViewHolder(view);   //passing our view to the viewHolder
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        holder.title.setText(list.get(position).title);


            holder.author.setText(list.get(position).author);


        Glide.with(mainContext).load(list.get(position).url).into(holder.imageView);
//setonclick listener
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(mainContext, intentclass.class);
                //AutoCompleteTextView textView = (AutoCompleteTextView) findViewById(R.id.autocomplete);
                //String getrec=textView.getText().toString();

//Create the bundle
                Bundle bundle = new Bundle();

//Add your data to bundle
                bundle.putString(des, list.get(position).getDescription());
                bundle.putString("title",list.get(position).getTitle());
                bundle.putString("author",list.get(position).getAuthor());
                bundle.putString("URL",list.get(position).getUrl());
                bundle.putString("LinkURL",list.get(position).getLinkURL());
                bundle.putString("publish",list.get(position).getPublished());
                //Toast.makeText(mainContext,  list.get(position).getUrl(), Toast.LENGTH_SHORT).show();
//Add the bundle to the intent
                i.putExtras(bundle);

//Fire that second activity
                mainContext.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}


class UserViewHolder extends RecyclerView.ViewHolder {
    public ImageView imageView;
    public TextView title,author;
    public CardView cardView;
    public UserViewHolder(@NonNull View itemView) {
        super(itemView);
        imageView=itemView.findViewById(R.id.imageView);
        author=itemView.findViewById(R.id.author);
        title=itemView.findViewById(R.id.titlename);
        cardView=itemView.findViewById(R.id.cardview);
    }
}