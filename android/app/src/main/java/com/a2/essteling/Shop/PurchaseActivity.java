package com.a2.essteling.Shop;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.a2.essteling.R;
import com.bumptech.glide.Glide;

public class PurchaseActivity extends AppCompatActivity {
    private ShopItem item;
    private TextView itemName;
    private TextView itemPrice;
    private TextView itemDescription;
    private ImageView itemImage;

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

        if (item.getImageLocal() == -1) {
            Glide.with(this).load(item.getImage()).into(itemImage);
        } else {
            itemImage.setImageResource(item.getImageLocal());
        }


    }
}