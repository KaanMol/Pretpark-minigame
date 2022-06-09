package com.a2.essteling;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.a2.essteling.Pass.PassActivity;
import com.a2.essteling.ScoreBoard.PlayerList;
import com.a2.essteling.ScoreBoard.ScoreboardActivity;
import com.a2.essteling.Shop.ShopActivity;
import com.a2.essteling.Shop.ShopitemList;

public class HomeActivity extends AppCompatActivity {
    private static final String LOG_TAG = HomeActivity.class.getSimpleName();
    private Button shopButton;
    private Button scoreButton;

    ShopitemList shopitemList = (ShopitemList) this.getApplication();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ShopitemList.startQueue(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.Red));

        shopButton = findViewById(R.id.shopButton);
        scoreButton = findViewById(R.id.scoreButton);

        if (PlayerList.players.size() == 0) {
//            shopButton.setClickable(false);
//            shopButton.setBackgroundTintList(this.getResources().getColorStateList(android.R.color.darker_gray));
            scoreButton.setClickable(false);
            scoreButton.setBackgroundTintList(this.getResources().getColorStateList(android.R.color.darker_gray));
        }
    }

    //go to the shop
    public void onShopButton(View view) {
        Intent intent = new Intent(this, ShopActivity.class);
        startActivity(intent);
    }

    //go to the scanner
    public void onScannerButton(View view) {
        Intent intent = new Intent(this, PassActivity.class);
        startActivity(intent);
    }

    //go to the scoreboard
    public void onScoreButton(View view) {
        Intent intent = new Intent(this, ScoreboardActivity.class);
        startActivity(intent);
    }
}