package com.winep.newsfeed.Adapter;

import com.bignerdranch.expandablerecyclerview.Model.ParentListItem;
import com.winep.newsfeed.DataModel.News;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ShaisteS on 6/11/2016.
 */
public class NewsGroupList implements ParentListItem {

    private ArrayList<News> news;

    public NewsGroupList(ArrayList<News> news){
        this.news=news;
    }

    @Override
    public ArrayList<News> getChildItemList() {
        return news;
    }

    @Override
    public boolean isInitiallyExpanded() {
        return false;
    }
}
