package com.winep.newsfeed.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.winep.newsfeed.DataModel.NewsGroup;
import com.winep.newsfeed.Presenter.Observer.ObserverAddNewsGroup;
import com.winep.newsfeed.R;

import java.util.ArrayList;

/**
 * Created by ShaisteS on 6/14/2016.
 */
public class NewsGroupAddAdapter extends RecyclerView.Adapter<NewsGroupAddAdapter.MyViewHolder> {

    private ArrayList<NewsGroup> newsList;
    private Context context;

    public NewsGroupAddAdapter(Context context,ArrayList<NewsGroup> newsList)
    {
        this.newsList = newsList;
        this.context=context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.settings_recycler_view_item_add, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final NewsGroup aNewsGroup = newsList.get(position);
        holder.newsTitle.setText(aNewsGroup.getTitle());
        holder.newsAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ObserverAddNewsGroup.setAddNewsGroup(aNewsGroup.getNewsGroupId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageButton newsAdd;
        public TextView newsTitle;

        public MyViewHolder(View view) {
            super(view);
            newsAdd=(ImageButton)view.findViewById(R.id.btn_delete_from_news_group_list);
            newsTitle=(TextView)view.findViewById(R.id.news_title);
        }
    }

    public void addItem(NewsGroup aNewsGroup){
        newsList.add(newsList.size(),aNewsGroup);
        notifyDataSetChanged();
    }

    public void removeANewsGroup(NewsGroup aNewsGroup){
        newsList.remove(aNewsGroup);
        notifyDataSetChanged();
    }
}
