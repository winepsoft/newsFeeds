package com.winep.newsfeed.DataModel;

import java.util.ArrayList;

/**
 * Created by ShaisteS on 6/11/2016.
 */
public class NewsGroup {

    private String title;
    private ArrayList<News> newsOfGroup;

    public NewsGroup(String newsGroupTitle,ArrayList<News> newsOfGroup){

        title=newsGroupTitle;
        this.newsOfGroup=newsOfGroup;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ArrayList<News> getNewsOfGroup() {
        return newsOfGroup;
    }

    public void setNewsOfGroup(ArrayList<News> newsOfGroup) {
        this.newsOfGroup = newsOfGroup;
    }
}
