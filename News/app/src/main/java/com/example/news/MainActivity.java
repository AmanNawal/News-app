package com.example.news;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public RecyclerView recyclerView;
    public Context context=MainActivity.this;

    //if textView overflows add ellipsize=end and singleline=true in xml textView

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView=findViewById(R.id.recycler);
        ArrayList<data> list=new ArrayList<data>();
        String url = "https://saurav.tech/NewsAPI/top-headlines/category/health/in.json";
        RequestQueue queue= Volley.newRequestQueue(this);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                      //  textView.setText("Response: " + response.toString());
                        //Toast.makeText(MainActivity.this, "Working", Toast.LENGTH_SHORT).show();
                        try {
                            JSONArray jsonArray=response.getJSONArray("articles");

                            for(int i=0;i<jsonArray.length();i++)
                            {

                                data data=new data();
                                data.setTitle(jsonArray.getJSONObject(i).getString("title"));
                                data.setAuthor(jsonArray.getJSONObject(i).getString("author"));
                                data.setUrl(jsonArray.getJSONObject(i).getString("urlToImage"));
                                data.setDescription(jsonArray.getJSONObject(i).getString("description"));
                                data.setLinkURL(jsonArray.getJSONObject(i).getString("url"));
                                data.setPublished(jsonArray.getJSONObject(i).getString("publishedAt"));
                                list.add(data);
                            }
                                adapter adapter=new adapter(list,MainActivity.this);
                           recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false));
                            recyclerView.setAdapter(adapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error
                        Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });

// Access the RequestQueue through your singleton class.
        //MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest);
        queue.add(jsonObjectRequest);
    }
}