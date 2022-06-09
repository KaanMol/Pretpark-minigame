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
import com.a2.essteling.ScoreBoard.PlayerList;

public class ShopActivity extends AppCompatActivity implements ShopItemListener, PointsListener, ShopItemListListener {
    private static final String LOG_TAG = ShopActivity.class.getSimpleName();
    private RecyclerView mRecyclerView;
    private ShopListAdapter mAdapter;
    private TextView totalPoints;

    private ShopItem[] items = ShopitemList.getShopItems();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.Red));

        ShopitemList.addListener(this);

        ShopitemList.startQueue(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);

        if(ShopitemList.getShopItems() != null) {

            //get the recyclerview
            mRecyclerView = findViewById(R.id.ShopRecyclerView);

            //create the adapter
            mAdapter = new ShopListAdapter(this, items, this);

            //give the adapter to the recyclerview
            mRecyclerView.setAdapter(mAdapter);

            //make the recyclerview a grid layout
            mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        } else{
            //get the recyclerview
            mRecyclerView = findViewById(R.id.ShopRecyclerView);

            //create the adapter
            mAdapter = new ShopListAdapter(this, new ShopItem[1], this);

            //give the adapter to the recyclerview
            mRecyclerView.setAdapter(mAdapter);

            //make the recyclerview a grid layout
            mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        }

        totalPoints = findViewById(R.id.totalPointsShop);

        totalPoints.setText("Total points: " + PlayerList.totalPoints());



    }

    @Override
    public void updatePoints(int totalPoints) {
        this.totalPoints.setText("Total Points :" + PlayerList.totalPoints());
    }

    //react to clicked items
    @Override
    public void onItemClicked(ShopItem item) {
        System.out.println(item.getName());
        Intent intent = new Intent(this, PurchaseActivity.class);
        intent.putExtra("item", item);

        startActivity(intent);
    }

    @Override
    public void updateShopItemList(ShopItem[] shopItems) {
        Log.d(LOG_TAG, "list updated");
        mAdapter.setShopItems(shopItems);
    }

    public void onBackButton(View view){
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);

    }

}