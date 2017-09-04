package com.nidhigondhia.android_rx_retrofit_demo.view;

import java.util.ArrayList;

/**
 * Created by Nidhi Gondhia on 04/09/2017.
 */

interface INewsMainAct {

    void hideProgressView(boolean hide);

    void configureRecyclerView();


    void successNewsIDAPICall(ArrayList<Integer> response);

    void failureNewsIDAPICall(Throwable throwable);
}
