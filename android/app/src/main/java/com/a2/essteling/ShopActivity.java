package com.a2.essteling;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.location.GnssAntennaInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.fasterxml.jackson.core.util.JsonParserSequence;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;

public class ShopActivity extends AppCompatActivity implements ShopItemListener {
    private static final String LOG_TAG = ShopActivity.class.getSimpleName();
    private RecyclerView mRecyclerView;
    private ShopListAdapter mAdapter;

    private ShopItem[] items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);

        items = new ShopItem[1];
        items[1] = new ShopItem("placeholder", "placeholder", 1,"placeholder", "placeholder");



//        RequestQueue requestQueue = Volley.newRequestQueue(this);
//        requestQueue.add(stringRequest);


//        for (int i = 0; i < 16; i++) {
//            items.add(new ShopItem("item" + i, i + "0,00", 1));
//        }


        //get the recyclerview
        mRecyclerView = findViewById(R.id.ShopRecyclerView);

        //create the adapter
        mAdapter = new ShopListAdapter(this, items, this);

        //give the adapter to the recyclerview
        mRecyclerView.setAdapter(mAdapter);

        //make the recyclerview a grid layout
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));






    }

    //react to clicked items
    @Override
    public void onItemClicked(ShopItem item) {
        System.out.println(item.getName());
        Intent intent = new Intent(this, PurchaseActivity.class);
        intent.putExtra("item", item);

        startActivity(intent);
    }

    public static StringRequest getShopItemsRequest(){
        return new StringRequest(Request.Method.GET, "https://mobiele-beleving-dev.herokuapp.com/products", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    Log.d(LOG_TAG, "response gotten");
                    ShopItem[] shopItems = new ObjectMapper().readValue(response, ShopItem[].class);



                    Log.d(LOG_TAG, "response succesfully received");

                } catch (IOException e) {
                    Log.d(LOG_TAG, "product loading failed!");
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(LOG_TAG, "error");
            }
        });
    }
}