package com.example.news;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class intentclass extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intentclass);
        Intent i=new Intent();
        Bundle bundle = getIntent().getExtras();

//Extract the data…
        String description = bundle.getString("description");
        String title = bundle.getString("title");
        String author = bundle.getString("author");
        String ulr1 = bundle.getString("URL");
        String linkurl=bundle.getString("LinkURL");
        String publishdate=bundle.getString("publish");

        String[] pub=publishdate.split("T");
        ImageView scrollImage=findViewById(R.id.imageView2);
        TextView title2,description2,author2,published1,linkURL1;
        title2=findViewById(R.id.scrollTitle);
        description2=findViewById(R.id.textView2);
        author2=findViewById(R.id.textView3);
        published1=findViewById(R.id.published);
        published1.setText("Published on - "+pub[0]);
        linkURL1=findViewById(R.id.link);
        linkURL1.setText("Click for complete article - "+linkurl);
        linkURL1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse(linkurl));
                startActivity(intent);
            }
        });




      Glide.with(intentclass.this).load(ulr1).into(scrollImage);
        title2.setText(title);
        description2.setText(description);
        if(author.equals(""))
        {
            author2.setText("Source - Unknown");
        }
        else {
            author2.setText("Source - " + author);
        }

    }
}