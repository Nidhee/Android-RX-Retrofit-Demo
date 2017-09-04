package com.nidhigondhia.android_rx_retrofit_demo.common;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by Nidhi Gondhia on 04/09/2017.
 */

public class Utils {

    public static final String  LOG_TAG = "NG_Demo";

    /**
     * Method to check is user connected to the network or not..?
     *
     * @param mContext
     * @return
     */
    public static boolean isOnline(Context mContext) {
        ConnectivityManager connMgr = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            return true;
        } else {
            return false;
        }
    }

}
