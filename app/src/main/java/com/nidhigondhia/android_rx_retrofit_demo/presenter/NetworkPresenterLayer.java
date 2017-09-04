package com.nidhigondhia.android_rx_retrofit_demo.presenter;

import android.app.Activity;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;
import com.nidhigondhia.android_rx_retrofit_demo.network.NetworkService;
import com.nidhigondhia.android_rx_retrofit_demo.view.NewsMainAct;

import java.util.ArrayList;

import rx.Observable;
import rx.Observer;
import rx.Subscription;

/**
 * Created by Nidhi Gondhia on 04/09/2017.
 */

public class NetworkPresenterLayer implements NetworkPresenterInteractor {

    private Object view;
    private NetworkService service;
    private Subscription subscriptionNewsID;

    public NetworkPresenterLayer(Activity view, NetworkService service){
        this.view = view;
        this.service = service;
    }

    @Override
    public void getTopStoriesNewsID() {

        Observable<JsonElement> newsIDResponseObservable = (Observable<JsonElement>)
                service.getPreparedObservable(service.getAPI().getTopStoriesNewsIDObservable(),JsonElement.class,true,true);

        subscriptionNewsID = newsIDResponseObservable.subscribe(new Observer<JsonElement>() {
            @Override
            public void onCompleted() {}

            @Override
            public void onError(Throwable e) {
                // redirect to view
                ((NewsMainAct)view).failureNewsIDAPICall(e);
            }

            @Override
            public void onNext(JsonElement jsonElement) {

                if(jsonElement.isJsonArray()){

                    Gson gson = new Gson();

                    TypeToken<ArrayList<Integer>> token = new TypeToken<ArrayList<Integer>>() {};
                    ArrayList<Integer> newsIDArrayList = gson.fromJson(jsonElement.getAsJsonArray(), token.getType());

                    // redirect to view
                    ((NewsMainAct)view).successNewsIDAPICall(newsIDArrayList);

                }
            }
        });
    }

    @Override
    public void rxUnSubscribeTopStoriesNewsID() {
        if(subscriptionNewsID!=null && !subscriptionNewsID.isUnsubscribed()) {
            subscriptionNewsID.unsubscribe();
        }
    }


}
