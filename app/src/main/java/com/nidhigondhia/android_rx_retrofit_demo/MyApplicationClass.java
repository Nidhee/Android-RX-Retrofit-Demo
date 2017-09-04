package com.nidhigondhia.android_rx_retrofit_demo;

import android.app.Application;
import android.content.Context;

import com.nidhigondhia.android_rx_retrofit_demo.network.NetworkService;

/**
 * Created by Nidhi Gondhia on 04/09/2017.
 */

public class MyApplicationClass extends Application {

    private static Context applicationContext;
    private NetworkService networkService;

    @Override
    public void onCreate() {
        super.onCreate();
        applicationContext = this.getApplicationContext();
        networkService = new NetworkService();
    }

    /**
     * Get application context
     * @return context
     */
    public static Context getContext() {
        return applicationContext;
    }

    /**
     * get instance of network service
     * @return network service
     */
    public NetworkService getNetworkService(){
        return networkService;
    }

}
