package com.winep.newsfeed.DataModel;

import java.util.ArrayList;

/**
 * Created by ShaisteS on 6/11/2016.
 */
public class NewsGroup {

    private String title;
    private int newsGroupId;

    public NewsGroup(String newsGroupTitle,int newsGroupId){

        title=newsGroupTitle;
        this.newsGroupId=newsGroupId;

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getNewsGroupId() {
        return newsGroupId;
    }

    public void setNewsGroupId(int newsGroupId) {
        this.newsGroupId = newsGroupId;
    }
}
