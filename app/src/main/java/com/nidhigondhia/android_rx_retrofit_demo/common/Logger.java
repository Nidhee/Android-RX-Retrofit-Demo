package com.nidhigondhia.android_rx_retrofit_demo.common;

import static android.util.Log.d;
import static android.util.Log.e;
import static android.util.Log.v;

/**
 * Created by Nidhi Gondhia on 04/09/2017.
 */

public class Logger {

    private static final boolean isLogEnable = true;

    public static final String TAG = Utils.LOG_TAG;

    public static void printLogV(String message) {
        if (isLogEnable)
            v(TAG, message);
    }

    public static void printLogE(String message) {
        if (isLogEnable)
            e(TAG, message);
    }

    public static void printLogD(String message) {
        if (isLogEnable)
            d(TAG, message);
    }
}
