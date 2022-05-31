package com.a2.essteling;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class HomeActivity extends AppCompatActivity {
    private static final String LOG_TAG = HomeActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    //go to the shop
    public void onShopButton(View view){
        Intent intent = new Intent(this, ShopActivity.class);
        startActivity(intent);

    }

    //go to the scanner
    public void onScannerButton(View view){
        Intent intent = new Intent(this,PassActivity.class);
        startActivity(intent);
    }
}