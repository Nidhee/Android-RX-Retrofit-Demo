package com.nidhigondhia.android_rx_retrofit_demo.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.nidhigondhia.android_rx_retrofit_demo.MyApplicationClass;
import com.nidhigondhia.android_rx_retrofit_demo.R;
import com.nidhigondhia.android_rx_retrofit_demo.adapter.NewsAdapter;
import com.nidhigondhia.android_rx_retrofit_demo.common.Logger;
import com.nidhigondhia.android_rx_retrofit_demo.common.Utils;
import com.nidhigondhia.android_rx_retrofit_demo.network.NetworkService;
import com.nidhigondhia.android_rx_retrofit_demo.presenter.NetworkPresenterInteractor;
import com.nidhigondhia.android_rx_retrofit_demo.presenter.NetworkPresenterLayer;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


public class NewsMainAct extends AppCompatActivity implements INewsMainAct {

    @BindView(R.id.rv_top_news_list)
    RecyclerView recyclerViewNewList;

    @BindView(R.id.pb_main_top_news_list)
    ProgressBar progressBar;

    NewsAdapter newsAdapter;

    private NetworkService service;
    private NetworkPresenterInteractor presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_main);
        ButterKnife.bind(this);

        service = ((MyApplicationClass)getApplication()).getNetworkService();
        presenter = new NetworkPresenterLayer(this, service);

        configureRecyclerView();
    }

    @Override
    protected void onStart() {
        super.onStart();

        if(Utils.isOnline(this)){
            hideProgressView(false);
            presenter.getTopStoriesNewsID();
        }
    }

    @Override
    public void hideProgressView(boolean hide){
        if(hide)
            progressBar.setVisibility(View.GONE);
        else
            progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void configureRecyclerView() {
        recyclerViewNewList.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewNewList.setItemAnimator(new DefaultItemAnimator());

        newsAdapter = new NewsAdapter();
        recyclerViewNewList.setAdapter(newsAdapter);
    }


    @Override
    public void successNewsIDAPICall(ArrayList<Integer> response) {

        hideProgressView(true);
        newsAdapter.setItems(response);
        newsAdapter.notifyDataSetChanged();
    }

    @Override
    public void failureNewsIDAPICall(Throwable throwable) {
        hideProgressView(true);
        Logger.printLogE("failureNewsIDAPICall >> failureNewsIDAPICall >> " + throwable);
    }
}
