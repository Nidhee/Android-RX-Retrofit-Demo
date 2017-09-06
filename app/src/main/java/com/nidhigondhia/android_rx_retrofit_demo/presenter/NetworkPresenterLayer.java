package com.nidhigondhia.android_rx_retrofit_demo.presenter;

import android.app.Activity;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;
import com.nidhigondhia.android_rx_retrofit_demo.common.Logger;
import com.nidhigondhia.android_rx_retrofit_demo.models.News;
import com.nidhigondhia.android_rx_retrofit_demo.network.NetworkService;
import com.nidhigondhia.android_rx_retrofit_demo.view.NewsMainAct;

import java.util.ArrayList;

import rx.Observable;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Nidhi Gondhia on 04/09/2017.
 */

public class NetworkPresenterLayer implements NetworkPresenterInteractor {

    private Object view;
    private NetworkService service;
    private Subscription subscriptionNewsID;
    private Subscription subscriptionNews;

    public NetworkPresenterLayer(Activity view, NetworkService service){
        this.view = view;
        this.service = service;
    }

    @Override
    public void getTopStoriesNewsID() {

        Observable<JsonElement> newsIDResponseObservable = (Observable<JsonElement>)
                service.getPreparedObservable(service.getAPI().getTopStoriesNewsIDObservable()
                        ,JsonElement.class,false,false);

        /*
        subscriptionNewsID =

                newsIDResponseObservable.map(item -> item.getAsLong())
                .doOnNext(item ->
                service.getPreparedObservable(
                        service.getAPI().getItemDetails(item),
                        JsonElement.class,true,true)
                        .subscribe())
        .subscribe();
        */

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

                    TypeToken<ArrayList<Long>> token = new TypeToken<ArrayList<Long>>() {};
                    ArrayList<Long> newsIDArrayList = gson.fromJson(jsonElement.getAsJsonArray(), token.getType());

                    // redirect to view
                    ((NewsMainAct)view).successNewsIDAPICall(newsIDArrayList);

                }
            }
        });
    }
    @Override
    public void getNewsDetail(Long id) {

        Observable<JsonElement> newsDetailResponseObservable = (Observable<JsonElement>)
                service.getPreparedObservable(service.getAPI().getItemDetails(id)
                        ,JsonElement.class,false,false);


        subscriptionNews = newsDetailResponseObservable.subscribe(new Observer<JsonElement>() {
            @Override
            public void onCompleted() {}

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(JsonElement jsonElement) {
                Logger.printLogE("json " + jsonElement.toString());

                Gson gson = new Gson();
                TypeToken<News> token = new TypeToken<News>() {};
                News news = gson.fromJson(jsonElement.getAsJsonObject(), token.getType());

                // redirect to view
                ((NewsMainAct)view).successNewsDetailAPICall(news);

            }
        });
    }
    /*
    @Override
    public void getTopStoriesNewsID() {

            subscriptionNewsID = service.getAPI().getTopStoriesNewsIDObservable()
                .map(jsonElement -> jsonElement.getAsJsonArray())
                .flatMapIterable(jsonArray -> jsonArray)
                .map(item -> item.getAsLong())
                .doOnNext(intNewsId ->
                                service.getAPI().getItemDetails(intNewsId)
                                        .subscribe(
                                                new Observer<JsonElement>() {
                                                    @Override
                                                    public void onCompleted() {
                                                        Log.e("TAG", "completed");
                                                    }

                                                    @Override
                                                    public void onError(Throwable e) {
                                                        Log.e("TAG", e.toString());
                                                    }

                                                    @Override
                                                    public void onNext(JsonElement jsonElement) {
                                                        Log.e("TAG", jsonElement.toString());
                                                    }
                                                }
                                        ))
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<Long>() {
                            @Override
                            public void onCompleted() {

                            }

                            @Override
                            public void onError(Throwable e) {
                                Log.e("TAG", e.toString());
                            }

                            @Override
                            public void onNext(Long aLong) {

                            }
                        });
    }
    */
    @Override
    public void rxUnSubscribeTopStoriesNewsID() {
        if(subscriptionNewsID!=null && !subscriptionNewsID.isUnsubscribed()) {
            subscriptionNewsID.unsubscribe();
        }
    }


}
