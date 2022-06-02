package com.a2.essteling;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.LinkedList;

public class ShopActivity extends AppCompatActivity implements ShopItemListener {
    private static final String LOG_TAG = ShopActivity.class.getSimpleName();
    private RecyclerView mRecyclerView;
    private ShopListAdapter mAdapter;
    private LinkedList<ShopItem> items = new LinkedList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);

//        for (int i = 0; i < 16; i++) {
//            items.add(new ShopItem("item" + i, i + "0,00", 1));
//        }

        //add items you want in the shop

        items.add(new ShopItem("burger", "12 punten","test", R.drawable.burger));
        items.add(new ShopItem("burger2", "12 punten", "test",R.drawable.burger2));
        items.add(new ShopItem("burger3", "12 punten", "test",R.drawable.burger3));
        items.add(new ShopItem("burger4", "12 punten", "test",R.drawable.burger4));
        items.add(new ShopItem("fries", "15 punten", "test",R.drawable.fries));
        items.add(new ShopItem("Unox", "8 punten", "test",R.drawable.sausage));
        items.add(new ShopItem("Nuggets", "6 punten", "test",R.drawable.nuggets));
        items.add(new ShopItem("Cola", "6 punten", "test",R.drawable.cola));
        items.add(new ShopItem("Fanta", "6 punten", "test",R.drawable.fanta));
        items.add(new ShopItem("Pepsi", "6 punten", "test",R.drawable.pepsi));
        items.add(new ShopItem("Ashizon's Paprika", "69 punten","test", R.drawable.ashizons_paprika_logo));
        items.add(new ShopItem("test", "test","test", "https://cdn.quicq.io/borgor/smashburger01.png"));

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
        Intent intent = new Intent(this,PurchaseActivity.class);
        intent.putExtra("item", item);

        startActivity(intent);
    }
}