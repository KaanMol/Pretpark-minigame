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

        items.add(new ShopItem("burger", "12 punten", R.drawable.burger));
        items.add(new ShopItem("burger2", "12 punten", R.drawable.burger2));
        items.add(new ShopItem("burger3", "12 punten", R.drawable.burger3));
        items.add(new ShopItem("burger4", "12 punten", R.drawable.burger4));
        items.add(new ShopItem("fries", "15 punten", R.drawable.fries));
        items.add(new ShopItem("Unox", "8 punten", R.drawable.sausage));
        items.add(new ShopItem("Nuggets", "6 punten", R.drawable.nuggets));
        items.add(new ShopItem("Cola", "6 punten", R.drawable.cola));
        items.add(new ShopItem("Fanta", "6 punten", R.drawable.fanta));
        items.add(new ShopItem("Pepsi", "6 punten", R.drawable.pepsi));

        mRecyclerView = findViewById(R.id.ShopRecyclerView);

        mAdapter = new ShopListAdapter(this, items, this);

        mRecyclerView.setAdapter(mAdapter);

        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
    }

    public void onHomeButton(View view){
        Intent intent = new Intent(this,HomeActivity.class);
        startActivity(intent);

    }

    @Override
    public void onItemClicked(ShopItem item) {
        System.out.println(item.getName());
    }
}