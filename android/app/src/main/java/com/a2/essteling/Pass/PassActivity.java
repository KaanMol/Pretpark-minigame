package com.a2.essteling.Pass;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.a2.essteling.R;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class PassActivity extends AppCompatActivity {
    private static final String LOG_TAG = PassActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
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

            String url = "https://mobiele-beleving-dev.herokuapp.com/cards?accountId=" + accountId + "&cardId=" + cardId;

            StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                    response -> {
                        Log.i(LOG_TAG, "Response: " + response);
                        Database.setName(cardId, name);
                    },
                    error -> {
                        Log.i(LOG_TAG, "Error: " + error);
                    });

            requestQueue.add(stringRequest);
        });
    }
}