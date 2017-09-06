package com.nidhigondhia.android_rx_retrofit_demo.view;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SimpleItemAnimator;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ProgressBar;

import com.nidhigondhia.android_rx_retrofit_demo.MyApplicationClass;
import com.nidhigondhia.android_rx_retrofit_demo.R;
import com.nidhigondhia.android_rx_retrofit_demo.adapter.NewsAdapter;
import com.nidhigondhia.android_rx_retrofit_demo.common.Logger;
import com.nidhigondhia.android_rx_retrofit_demo.common.Utils;
import com.nidhigondhia.android_rx_retrofit_demo.models.News;
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

    private DividerItemDecoration dividerItemDecoration;
    private LinearLayoutManager mLayoutManager;

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
    protected void onPause() {
        super.onPause();

        presenter.rxUnSubscribeTopStoriesNewsID();
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

        // setting linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        recyclerViewNewList.setLayoutManager(mLayoutManager);
       // recyclerViewNewList.setItemAnimator(new DefaultItemAnimator());

        // setting divider decoration
        dividerItemDecoration = new DividerItemDecoration(recyclerViewNewList.getContext(), mLayoutManager.getOrientation());
        recyclerViewNewList.addItemDecoration(dividerItemDecoration);

        ((SimpleItemAnimator) recyclerViewNewList.getItemAnimator()).setSupportsChangeAnimations(false);

        // intializing adapter, and set adapter to recycler view
        newsAdapter = new NewsAdapter(presenter);
        recyclerViewNewList.setAdapter(newsAdapter);

        recyclerViewNewList.addOnItemTouchListener(new RecyclerTouchListener(this, recyclerViewNewList, new ClickListener() {
            @Override
            public void onItemClick(View view, int position) {

                // start new activity with webview

            }
        }));
    }

    @Override
    public void successNewsIDAPICall(ArrayList<Long> response) {

        // hide progress bar on response
        hideProgressView(true);

        // set response items to adapter and refresh adapter
        newsAdapter.setItems(response);
        newsAdapter.notifyDataSetChanged();
    }

    @Override
    public void failureNewsIDAPICall(Throwable throwable) {
        // hide progress bar on response
        hideProgressView(true);
        Logger.printLogE("failureNewsIDAPICall >> failureNewsIDAPICall >> " + throwable);
    }

    @Override
    public void successNewsDetailAPICall(News response) {
        newsAdapter.updateNewsHashMap(response,response.getId());
    }

    @Override
    public void failureNewsDetailAPICall(Throwable throwable) {

    }

    public static interface ClickListener{
        public void onItemClick(View view,int position);
    }

    /**
     * Recycler touch listner
     */
    class RecyclerTouchListener implements RecyclerView.OnItemTouchListener{

        private ClickListener clicklistener;
        private GestureDetector gestureDetector;
        public RecyclerTouchListener(Context context, final RecyclerView recycleView, final ClickListener clicklistener){
            this.clicklistener=clicklistener;
            gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }
            });
        }
        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
            View child=rv.findChildViewUnder(e.getX(),e.getY());
            if(child!=null && clicklistener!=null && gestureDetector.onTouchEvent(e)){
                clicklistener.onItemClick(child,rv.getChildAdapterPosition(child));
            }

            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {

        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

        }
    }

}
