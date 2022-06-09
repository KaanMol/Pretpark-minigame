package com.a2.essteling.Shop;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.a2.essteling.HomeActivity;
import com.a2.essteling.R;
import com.a2.essteling.ScoreBoard.PlayerList;
import com.a2.essteling.ScoreBoard.ScoreboardActivity;
import com.bumptech.glide.Glide;

public class PurchaseActivity extends AppCompatActivity implements PointsListener{
    private ShopItem item;
    private TextView itemName;
    private TextView itemPrice;
    private TextView itemDescription;
    private ImageView itemImage;
    private TextView totalPoints;
    private static final String LOG_TAG = PurchaseActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.Red));

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase);

        this.item = (ShopItem) getIntent().getExtras().get("item");

        itemName = findViewById(R.id.itemNameBuy);
        itemPrice = findViewById(R.id.itemPriceBuy);
        itemDescription = findViewById(R.id.itemDescription);
        itemImage = findViewById(R.id.itemImageBuy);

        itemName.setText(item.getName());
        itemPrice.setText(item.getPrice()+ " points");
        itemDescription.setText(item.getDescription());

        totalPoints = findViewById(R.id.totalPointsPurchase);
        totalPoints.setText("Total Points :" + PlayerList.totalPoints());

        if (item.getImageLocal() == -1) {
            Glide.with(this).load(item.getImage()).into(itemImage);
        } else {
            itemImage.setImageResource(item.getImageLocal());
        }

        PlayerList.addPointsListener(this);
    }
    public void onCouponButton(View view){
        if(PlayerList.totalPoints() - item.getPrice() >= 0) {
            PlayerList.spendPoints(item.getPrice());
            Intent intent = new Intent(this, CouponActivity.class);
            intent.putExtra("item", item);
            startActivity(intent);
        } else{
            Toast.makeText(this, "Not enough points!", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void updatePoints(int totalPoints) {
        this.totalPoints.setText("Total Points :" + PlayerList.totalPoints());
    }

    public void onBackButton(View view){
        Intent intent = new Intent(this, ShopActivity.class);
        startActivity(intent);

    }

}