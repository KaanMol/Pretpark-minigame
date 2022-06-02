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

    private ShopItem[] items = ShopitemList.getShopItems();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);

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
}