package com.nidhigondhia.android_rx_retrofit_demo.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.nidhigondhia.android_rx_retrofit_demo.MyApplicationClass;
import com.nidhigondhia.android_rx_retrofit_demo.R;
import com.nidhigondhia.android_rx_retrofit_demo.common.Utils;
import com.nidhigondhia.android_rx_retrofit_demo.presenter.NetworkPresenterLayer;

import butterknife.ButterKnife;

/**
 * Created by Nidhi Gondhia on 06/09/2017.
 */

public class NewsDetailAct extends AppCompatActivity implements INewsDetailAct {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_main);
        ButterKnife.bind(this);


    }

    @Override
    protected void onStart() {
        super.onStart();


    }


}
