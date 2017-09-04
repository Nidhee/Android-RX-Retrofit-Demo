package com.nidhigondhia.android_rx_retrofit_demo.view;

import java.util.ArrayList;

/**
 * 
 * Created by Nidhi Gondhia on 04/09/2017.
 */

interface INewsMainAct {

    /**
     * Hide/show progress bar view in main UI
     * @param hide, true to hide view and false otherwise
     */
    void hideProgressView(boolean hide);

    /**
     * Configure recycler view, i.e. setting layout manager, divider decoration,adapter
     */
    void configureRecyclerView();

    /**
     * Success Response callback for News ID initial API call
     * @param response response arraylist
     */
    void successNewsIDAPICall(ArrayList<Integer> response);

    /**
     * Success Response callback for News ID initial API call
     * @param throwable error throwable
     */
    void failureNewsIDAPICall(Throwable throwable);
}
