package com.justkeepfaith.krygdistwe;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class Conectivity {

    public static String activityname = null;
    private static boolean isConnected = false;

    public static boolean isConnectingToInternet(Context context) {
        NetworkInfo[] allNetworkInfo;
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (!(connectivityManager == null || (allNetworkInfo = connectivityManager.getAllNetworkInfo()) == null)) {
            for (NetworkInfo state : allNetworkInfo) {
                if (state.getState() == NetworkInfo.State.CONNECTED) {
                    return true;
                }
            }
        }
        return false;
    }
}
