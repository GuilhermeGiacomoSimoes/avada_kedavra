package com.example.avadakedavra.helper;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class ConnectionHelper {
    public static String ALLCHARACTERS = "http://hp-api.herokuapp.com/api/characters";
    public static String CHARACTERSBYHOUSE = "http://hp-api.herokuapp.com/api/characters/house/";
    public static String STUDENTCHARACTERS = "http://hp-api.herokuapp.com/api/characters/students";

    public static Boolean isConnected(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = null;

        if(connectivityManager != null)
            networkInfo = connectivityManager.getActiveNetworkInfo();

        return networkInfo != null && networkInfo.isConnectedOrConnecting();
    }

}
