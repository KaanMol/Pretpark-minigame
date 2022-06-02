package com.a2.essteling;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class PurchaseActivity extends AppCompatActivity {
    private ShopItem item;
    private TextView itemName;
    private TextView itemPrice;
    private TextView itemDescription;
    private ImageView itemImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase);
        this.item = (ShopItem) getIntent().getExtras().get("item");

        itemName = findViewById(R.id.itemNameBuy);
        itemPrice = findViewById(R.id.itemPriceBuy);
        itemDescription = findViewById(R.id.itemDescription);
        itemImage = findViewById(R.id.itemImageBuy);

        itemName.setText(item.getName());
        itemPrice.setText(item.getPrice());
        itemDescription.setText(item.getDescription());
        if (item.getImage() == -1) {
            Glide.with(this).load(item.getImageURL()).into(itemImage);
        } else {
            itemImage.setImageResource(item.getImage());
        }


    }
}