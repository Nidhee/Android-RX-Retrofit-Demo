package com.nidhigondhia.android_rx_retrofit_demo.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nidhigondhia.android_rx_retrofit_demo.R;
import com.nidhigondhia.android_rx_retrofit_demo.models.News;
import com.nidhigondhia.android_rx_retrofit_demo.presenter.NetworkPresenterInteractor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Nidhi Gondhia on 04/09/2017.
 */

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.MyNewsViewHolder> {

    private List<Long> newsIDArrayList = new ArrayList<>();
    private HashMap<Long,News> newsHashMap = new HashMap<>();

    private NetworkPresenterInteractor presenter;

    public class MyNewsViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_news_item_title)
        TextView tvTitle;

        @BindView(R.id.pb_news_item)
        ProgressBar pbItem;

        public MyNewsViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
    public NewsAdapter(NetworkPresenterInteractor presenterInteractor){
        presenter = presenterInteractor;
    }

    public void setItems(final ArrayList<Long> list) {
        this.newsIDArrayList = list;
    }
    @Override
    public MyNewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_item, parent, false);
        return new MyNewsViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyNewsViewHolder holder, int position) {

        if(newsHashMap!=null && newsHashMap.containsKey(newsIDArrayList.get(position))) {

            holder.pbItem.setVisibility(View.INVISIBLE);
            holder.tvTitle.setText(newsHashMap.get(newsIDArrayList.get(position)).getTitle());

        }else {

            holder.tvTitle.setText("");
            holder.pbItem.setVisibility(View.VISIBLE);
            presenter.getNewsDetail(newsIDArrayList.get(position));
        }
    }

    public void updateNewsHashMap(News response,Long id){

        newsHashMap.put(id,response);

        int pos = newsIDArrayList.indexOf(id);
        notifyItemChanged(pos);


    }
    @Override
    public int getItemCount() {
        return newsIDArrayList.size();
    }
}
