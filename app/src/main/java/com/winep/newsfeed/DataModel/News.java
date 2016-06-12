package com.winep.newsfeed.DataModel;

/**
 * Created by ShaisteS on 6/11/2016.
 */
public class News {

    private String title;
    private String brief;
    private String imageLink;
    private String resource;
    private String date;
    private String descriptionLink;

    public News(String newsTitle,String newsBrief,String newsImageLink,String newsResource,String newsDate,String newsDescriptionLink){
        title=newsTitle;
        brief=newsBrief;
        imageLink=newsImageLink;
        resource=newsResource;
        date=newsDate;
        descriptionLink=newsDescriptionLink;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescriptionLink() {
        return descriptionLink;
    }

    public void setDescriptionLink(String descriptionLink) {
        this.descriptionLink = descriptionLink;
    }
}
