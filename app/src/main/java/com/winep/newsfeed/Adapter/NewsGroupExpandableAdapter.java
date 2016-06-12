package com.winep.newsfeed.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bignerdranch.expandablerecyclerview.Adapter.ExpandableRecyclerAdapter;
import com.bignerdranch.expandablerecyclerview.Model.ParentListItem;
import com.winep.newsfeed.DataModel.News;
import com.winep.newsfeed.DataModel.NewsGroup;
import com.winep.newsfeed.R;

import java.util.List;

/**
 * Created by ShaisteS on 6/11/2016.
 */
public class NewsGroupExpandableAdapter extends ExpandableRecyclerAdapter<NewsGroupParentHolder, NewsGroupChildHolder> {

    private LayoutInflater mInflater;
    private Context context;


    public NewsGroupExpandableAdapter(Context context ,@NonNull List<? extends ParentListItem> parentItemList) {
        super(parentItemList);
        mInflater = LayoutInflater.from(context);
        this.context=context;
    }

    @Override
    public NewsGroupParentHolder onCreateParentViewHolder(ViewGroup parentViewGroup) {
        View view = mInflater.inflate(R.layout.news_group_item, parentViewGroup, false);
        return new NewsGroupParentHolder(view);
    }

    @Override
    public NewsGroupChildHolder onCreateChildViewHolder(ViewGroup childViewGroup) {
        View view = mInflater.inflate(R.layout.news_information_item, childViewGroup, false);
        return new NewsGroupChildHolder(view);
    }

    @Override
    public void onBindParentViewHolder(NewsGroupParentHolder parentViewHolder, int position, ParentListItem parentListItem) {

        NewsGroup  newsGroup = (NewsGroup) parentListItem;
        parentViewHolder.bind(context,newsGroup);

    }

    @Override
    public void onBindChildViewHolder(NewsGroupChildHolder childViewHolder, int position, Object childListItem) {

        News news = (News) childListItem;
        childViewHolder.bind(context,news);

    }
}
