package com.a2.essteling.Shop;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.a2.essteling.R;
import com.bumptech.glide.Glide;

public class CouponActivity extends AppCompatActivity {
    private ImageView itemImage;
    private ShopItem item;
    private TextView itemDescription;
    private TextView itemName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coupon);
        this.item = (ShopItem) getIntent().getExtras().get("item");
        itemImage = findViewById(R.id.itemImageBuy);
        itemDescription = findViewById(R.id.itemDescription);
        itemDescription.setText(item.getDescription());
        itemName = findViewById(R.id.itemNameBuy);
        itemName.setText(item.getName());
        if (item.getImageLocal() == -1) {
            Glide.with(this).load(item.getImage()).into(itemImage);
        } else {
            itemImage.setImageResource(item.getImageLocal());
        }

    }
}