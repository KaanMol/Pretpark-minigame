package com.a2.essteling;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ShopitemList extends Application {
    private static final String LOG_TAG = ShopitemList.class.getSimpleName();
    private static ShopItem[] shopItems;
    private static StringRequest request;

    public ShopitemList() {
        updateList();
    }

    public static ShopItem[] getShopItems() {
        return shopItems;
    }

    public static void updateList() {
        request = new StringRequest(Request.Method.GET, "https://mobiele-beleving-dev.herokuapp.com/products", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    Log.d(LOG_TAG, "response gotten");
                    ShopItem[] items = new ObjectMapper().readValue(response, ShopItem[].class);

                    shopItems = items;

                    Log.d(LOG_TAG, "response succesfully received");

                } catch (IOException e) {
                    Log.d(LOG_TAG, "product loading failed!");
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(LOG_TAG, "error " + LocalTime.now());
            }
        });
        Log.d(LOG_TAG, "request made " + LocalTime.now());
    }

    public static  void startQueue(Context context){
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(request);
        Log.d(LOG_TAG, "started queue " + LocalTime.now());
    }}
