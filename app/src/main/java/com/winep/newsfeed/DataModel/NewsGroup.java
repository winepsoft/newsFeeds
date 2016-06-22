package com.winep.newsfeed.DataModel;

/**
 * Created by ShaisteS on 6/11/2016.
 */
public class NewsGroup {

    private String title;
    private int newsGroupId;
    private int priority;
    private int isUserFavorite;// 0=no favorite,1=yes favorite

    public NewsGroup(){
    }

    public NewsGroup(String newsGroupTitle,int newsGroupId,int newsGroupPriority,int newsGroupIsUserFavorite){

        title=newsGroupTitle;
        this.newsGroupId=newsGroupId;
        priority=newsGroupPriority;
        isUserFavorite=newsGroupIsUserFavorite;

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

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public int getIsUserFavorite() {
        return isUserFavorite;
    }

    public void setIsUserFavorite(int isUserFavorite) {
        this.isUserFavorite = isUserFavorite;
    }
}
