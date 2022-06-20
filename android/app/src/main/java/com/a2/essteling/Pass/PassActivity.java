package com.a2.essteling.Pass;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.a2.essteling.HomeActivity;
import com.a2.essteling.PlayerData.Player;
import com.a2.essteling.R;
import com.a2.essteling.PlayerData.PlayerList;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.UnsupportedEncodingException;

public class PassActivity extends AppCompatActivity {
    private static final String LOG_TAG = PassActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(LOG_TAG, "Opened pass activity");
        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.Red));

        //add a list of testplayers
//        PlayerList.testPlayers();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pass);

        //Get the used views
        Button button = findViewById(R.id.buttonConnect);
        TextView textName = findViewById(R.id.textName);
        TextView textCard = findViewById(R.id.textCard);

        RequestQueue requestQueue = Volley.newRequestQueue(this);

        button.setOnClickListener(click -> {
            Log.d(LOG_TAG, "Connect button clicked");
            //The needed data from the user
            String accountId = Settings.Secure.getString(getApplicationContext().getContentResolver(), Settings.Secure.ANDROID_ID);
            String name = textName.getText().toString();
            String cardId = textCard.getText().toString();

            Log.i(LOG_TAG, "AccountId: " + accountId);
            Log.i(LOG_TAG, "Name: " + name);

            //The server url
            String url = "https://mobiele-beleving-dev.herokuapp.com/cards?accountId=" + accountId + "&cardNumber=" + cardId;

            //React to a response from the server
            StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                    response -> {
                        Log.d(LOG_TAG, "Response: " + response);

                        //get the nfcid from the response
                        String[] nfcId = response.split("\"");
                        PlayerList.addPlayer(new Player(name, nfcId[3], this));

                        Toast.makeText(this, R.string.cardRegistered, Toast.LENGTH_LONG).show();

                        Intent intent = new Intent(this, HomeActivity.class);
                        startActivity(intent);
                    },
                    error -> {
                        Log.d(LOG_TAG, "Error: " + error);
                        String body;
                        //get status code here
                        final String statusCode = String.valueOf(error.networkResponse.statusCode);
                        //get response body and parse with appropriate encoding
                        try {
                            //Show server error
                            body = new String(error.networkResponse.data,"UTF-8");
                            Log.d(LOG_TAG, body);
                            Toast.makeText(getBaseContext(), body, Toast.LENGTH_LONG).show();
                        } catch (UnsupportedEncodingException e) {
                            //debug if the server error is unreadable
                            Log.d(LOG_TAG, e.toString());
                        }
                    });

            requestQueue.add(stringRequest);
        });
    }

    public void onBackButton(View view){
        Log.i(LOG_TAG, "Back button pressed");
        this.finish();

    }
}