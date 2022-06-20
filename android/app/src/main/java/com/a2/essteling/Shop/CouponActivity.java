package com.a2.essteling.Shop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.a2.essteling.R;
import com.a2.essteling.PlayerData.PlayerList;
import com.bumptech.glide.Glide;

public class CouponActivity extends AppCompatActivity {
    private ImageView itemImage;
    private ShopItem item;
    private TextView itemDescription;
    private TextView itemName;
    private TextView totalPoints;
    private static final String LOG_TAG = CouponActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(LOG_TAG, "Opened coupon activity");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coupon);

        //get the item fro the coupon
        this.item = (ShopItem) getIntent().getExtras().get("item");

        //get the layout items and set the text
        itemImage = findViewById(R.id.itemImageBuy);
        itemDescription = findViewById(R.id.itemDescription);
        itemDescription.setText(item.getDescription());
        itemName = findViewById(R.id.itemNameBuy);
        itemName.setText(item.getName());

        //set the total points
        totalPoints = findViewById(R.id.totalPointsCoupon);
        totalPoints.setText(getString(R.string.totalPoints) +" "+ PlayerList.totalPoints());

        //set the image
        if (item.getImageLocal() == -1) {
            Glide.with(this).load(item.getImage()).into(itemImage);
        } else {
            itemImage.setImageResource(item.getImageLocal());
        }

    }

    public void onBackButton(View view){
        Log.i(LOG_TAG, "Back button pressed");

        this.finish();
    }

}