package com.example.vahakassignment;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

public class NetworkConnection {

    private static NetworkConnection instance;
    private static Context context;
    private boolean connected = false;

    public static NetworkConnection getInstance(Context ctx) {
        if (instance == null) {
            instance = new NetworkConnection();
            context = ctx.getApplicationContext();
        }
        return instance;
    }

    public boolean isConnected() {
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);

            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            connected = networkInfo != null && networkInfo.isAvailable() &&
                    networkInfo.isConnected();
            return connected;


        } catch (Exception e) {
            Log.v("connectivity", e.toString());
        }
        return connected;
    }
}
