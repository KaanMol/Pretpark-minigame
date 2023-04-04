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

import com.a2.essteling.R;
import com.a2.essteling.PlayerData.PlayerList;
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
        Log.i(LOG_TAG, "Opened purchase activity");
        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.Red));

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase);

        //get the given item for the purchase
        this.item = (ShopItem) getIntent().getExtras().get("item");

        //get all the layout items
        itemName = findViewById(R.id.itemNameBuy);
        itemPrice = findViewById(R.id.itemPriceBuy);
        itemDescription = findViewById(R.id.itemDescription);
        itemImage = findViewById(R.id.itemImageBuy);

        //set the text to the layout itmes
        itemName.setText(item.getName());
        itemPrice.setText("\n"+item.getPrice()+" "+ getString(R.string._points)+"\n");
        itemDescription.setText(item.getDescription()+"\n\n\n\n");

        //show the total points
        totalPoints = findViewById(R.id.totalPointsPurchase);
        totalPoints.setText(getString(R.string.totalPoints) + " " +PlayerList.totalPoints());

        //set the image
        if (item.getImageLocal() == -1) {
            Glide.with(this).load(item.getImage()).into(itemImage);
        } else {
            itemImage.setImageResource(item.getImageLocal());
        }

        //add a listener to change points when an item is bought
        PlayerList.addPointsListener(this);
    }

    //give the item to and open the coupon page
    public void onCouponButton(View view){
        //check if the user has enough points
        if(PlayerList.totalPoints() - item.getPrice() >= 0) {
            PlayerList.spendPoints(item.getPrice());
            Intent intent = new Intent(this, CouponActivity.class);
            intent.putExtra("item", item);
            startActivity(intent);
        } else{
            Toast.makeText(this, R.string.notEnoughPoints, Toast.LENGTH_LONG).show();
        }
    }

    //listener to changed points
    @Override
    public void updatePoints(int totalPoints) {
        this.totalPoints.setText(getString(R.string.totalPoints) + " "+ PlayerList.totalPoints());
    }

    public void onBackButton(View view){
        Log.i(LOG_TAG, "Back button pressed");
        this.finish();
    }

}