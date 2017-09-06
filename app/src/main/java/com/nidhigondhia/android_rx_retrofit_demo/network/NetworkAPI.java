package com.nidhigondhia.android_rx_retrofit_demo.network;

import com.google.gson.JsonElement;
import com.nidhigondhia.android_rx_retrofit_demo.models.News;

import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import rx.Observable;

/**
 * All the Service calls to use for the retrofit requests, should be listed here.
 * Created by Nidhi Gondhia on 04/09/2017.
 */

public interface NetworkAPI {

    @GET("topstories.json")
    @Headers({"Content-Type:application/json; charset=UTF-8"})
    Observable<JsonElement> getTopStoriesNewsIDObservable();

    @GET("/v0/item/{itemId}.json")
    @Headers({"Content-Type:application/json; charset=UTF-8"})
    Observable<JsonElement> getItemDetails(@Path("itemId") Long itemId);
}
