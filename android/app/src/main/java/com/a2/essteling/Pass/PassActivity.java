package com.a2.essteling.Pass;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.a2.essteling.R;
import com.a2.essteling.ScoreBoard.PlayerList;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.UnsupportedEncodingException;

public class PassActivity extends AppCompatActivity {
    private static final String LOG_TAG = PassActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.Red));

        PlayerList.testPlayers();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pass);

        Button button = findViewById(R.id.buttonConnect);
        TextView textName = findViewById(R.id.textName);
        TextView textCard = findViewById(R.id.textCard);

        RequestQueue requestQueue = Volley.newRequestQueue(this);

        button.setOnClickListener(click -> {
            String accountId = Settings.Secure.getString(getApplicationContext().getContentResolver(), Settings.Secure.ANDROID_ID);
            String name = textName.getText().toString();
            String cardId = textCard.getText().toString();

            Log.i(LOG_TAG, "AccountId: " + accountId);
            Log.i(LOG_TAG, "Name: " + name);

            String url = "https://mobiele-beleving-dev.herokuapp.com/cards?accountId=" + accountId + "&cardNumber=" + cardId;

            StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                    response -> {
                        Log.d(LOG_TAG, "Response: " + response);
                        PlayerList.addPlayer(new Player(name, cardId, this));
                    },
                    error -> {
                        Log.d(LOG_TAG, "Error: " + error);
                        String body;
                        //get status code here
                        final String statusCode = String.valueOf(error.networkResponse.statusCode);
                        //get response body and parse with appropriate encoding
                        try {
                            body = new String(error.networkResponse.data,"UTF-8");
                            Log.d(LOG_TAG, body);
                            Toast.makeText(getBaseContext(), body, Toast.LENGTH_LONG).show();
                        } catch (UnsupportedEncodingException e) {
                            // exception
                            Log.d(LOG_TAG, "huh????");
                        }
                    });

            requestQueue.add(stringRequest);
        });
    }
}