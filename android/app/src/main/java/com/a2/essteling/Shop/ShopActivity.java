package com.a2.essteling.Shop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.a2.essteling.HomeActivity;
import com.a2.essteling.R;
import com.a2.essteling.PlayerData.PlayerList;

public class ShopActivity extends AppCompatActivity implements ShopItemListener, PointsListener, ShopItemListListener {
    private static final String LOG_TAG = ShopActivity.class.getSimpleName();
    private RecyclerView mRecyclerView;
    private ShopListAdapter mAdapter;
    private TextView totalPoints;

    private ShopItem[] items = ShopitemList.getShopItems();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(LOG_TAG, "Opened shop activity");
        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.Red));

        //listener to change list
        ShopitemList.addListener(this);

        ShopitemList.startQueue(this);

        //listener to update points when changed
        PlayerList.addPointsListener(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);

        //check if the items have loaded
        if(ShopitemList.getShopItems() != null) {
            //get the recyclerview
            mRecyclerView = findViewById(R.id.ShopRecyclerView);
            //create the adapter
            mAdapter = new ShopListAdapter(this, items, this);
            //give the adapter to the recyclerview
            mRecyclerView.setAdapter(mAdapter);
            //make the recyclerview a grid layout
            mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        }

        totalPoints = findViewById(R.id.totalPointsShop);

        totalPoints.setText((getString(R.string.totalPoints)) + " "+PlayerList.totalPoints());



    }

    //update points when changed
    @Override
    public void updatePoints(int totalPoints) {
        this.totalPoints.setText((getString(R.string.totalPoints)) +" "+ PlayerList.totalPoints());
    }

    //react to clicked items
    @Override
    public void onItemClicked(ShopItem item) {
        System.out.println(item.getName());
        Intent intent = new Intent(this, PurchaseActivity.class);
        intent.putExtra("item", item);

        startActivity(intent);
    }

    //show the newly loaded items
    @Override
    public void updateShopItemList(ShopItem[] shopItems) {
        Log.d(LOG_TAG, "list updated");

        this.items = shopItems;
        //get the recyclerview
        mRecyclerView = findViewById(R.id.ShopRecyclerView);
        //create the adapter
        mAdapter = new ShopListAdapter(this, shopItems, this);
        //give the adapter to the recyclerview
        mRecyclerView.setAdapter(mAdapter);
        //make the recyclerview a grid layout
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
    }

    public void onBackButton(View view){
        Log.i(LOG_TAG, "Back button pressed");
        this.finish();
    }

}