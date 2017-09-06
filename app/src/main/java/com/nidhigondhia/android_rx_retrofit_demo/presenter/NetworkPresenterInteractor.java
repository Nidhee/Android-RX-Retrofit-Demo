package com.nidhigondhia.android_rx_retrofit_demo.presenter;

/**
 * Created by Nidhi Gondhia on 04/09/2017.
 */

public interface NetworkPresenterInteractor {

    void getTopStoriesNewsID();
    void rxUnSubscribeTopStoriesNewsID();


    void getNewsDetail(Long newsID);
}
