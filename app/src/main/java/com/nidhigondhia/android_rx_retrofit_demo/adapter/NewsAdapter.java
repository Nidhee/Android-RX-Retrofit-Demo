package com.nidhigondhia.android_rx_retrofit_demo.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nidhigondhia.android_rx_retrofit_demo.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Nidhi Gondhia on 04/09/2017.
 */

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.MyNewsViewHolder> {

    private List<Integer> newsIDArrayList = new ArrayList<>();

    public class MyNewsViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_news_item_title)
        TextView tvTitle;

        public MyNewsViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
    public NewsAdapter(){}

    public void setItems(final ArrayList<Integer> list) {
        this.newsIDArrayList = list;
    }
    @Override
    public MyNewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_item, parent, false);
        return new MyNewsViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyNewsViewHolder holder, int position) {
        holder.tvTitle.setText(String.valueOf(newsIDArrayList.get(position)));
    }

    @Override
    public int getItemCount() {
        return newsIDArrayList.size();
    }
}
