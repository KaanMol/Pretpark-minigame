package com.a2.essteling.PlayerData;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.time.LocalTime;
import java.util.LinkedList;

public class HistoryList extends Application {
    private final String LOG_TAG = HistoryList.class.getSimpleName();
    private LinkedList<History> histories;
    private StringRequest request;
    private HistoryListener listener;

    public HistoryList(String nfcId, HistoryListener listener, Context context) {
        this.listener = listener;
        updateList(nfcId);
        startQueue(context);
    }

    private LinkedList<History> getHistories() {
        return histories;
    }

    //Start a new request to get the latest history on all players
    private void updateList(String nfcId) {
        Log.i(LOG_TAG, "Update history list");
        request = new StringRequest(Request.Method.GET, "https://mobiele-beleving-dev.herokuapp.com/points?nfcId="+nfcId, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                try {
                    Log.d(LOG_TAG, "response gotten");
                    History[] items = new ObjectMapper().readValue(response, History[].class);

                    histories = new LinkedList<>();

                    for (int i = 0; i < items.length; i++) {
                        histories.add(new History(items[i].getGameId(), "noLocation", items[i].getTimestamp().substring(11, 16), items[i].getPoints(), items[i].getNfcId()));
                    }

                    if(items.length > 0) {
                        Log.d(LOG_TAG, "response succesfully received " + items[0].getNfcId());
                    }
                    listener.onHistoryReceived(histories);

                } catch (IOException e) {
                    Log.d(LOG_TAG, "history loading failed!");
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            //report loading error
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(LOG_TAG, "error " + LocalTime.now());
                Log.d(LOG_TAG, error.toString());
            }
        });
        Log.d(LOG_TAG, "request made " + LocalTime.now());
    }

    private void startQueue(Context context){
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(request);
        Log.d(LOG_TAG, "started queue " + LocalTime.now());
    }}
