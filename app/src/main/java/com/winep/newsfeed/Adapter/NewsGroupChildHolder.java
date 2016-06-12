package com.winep.newsfeed.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.bignerdranch.expandablerecyclerview.ViewHolder.ChildViewHolder;
import com.winep.newsfeed.DataModel.News;
import com.winep.newsfeed.Presenter.NewsOfAGroupActivity;
import com.winep.newsfeed.Presenter.WebView;
import com.winep.newsfeed.R;

/**
 * Created by ShaisteS on 6/11/2016.
 */
public class NewsGroupChildHolder extends ChildViewHolder {

    public ImageButton newsShare;
    public ImageButton newsBookMark;
    public ImageButton newsImage;
    public TextView newsTitle;
    public TextView newsBrief;
    public TextView newsTime;
    public TextView newsResource;


    public NewsGroupChildHolder(View itemView) {
        super(itemView);
        newsShare=(ImageButton)itemView.findViewById(R.id.newsShare);
        newsBookMark=(ImageButton)itemView.findViewById(R.id.newsBookMark);
        newsImage=(ImageButton)itemView.findViewById(R.id.newsImage);
        newsTitle=(TextView)itemView.findViewById(R.id.newsTitle);
        newsBrief=(TextView)itemView.findViewById(R.id.newsBrief);
        newsTime=(TextView)itemView.findViewById(R.id.newsTime);
        newsResource=(TextView)itemView.findViewById(R.id.newsResource);

    }

    public void bind(final Context context, final News aNews){

        newsTitle.setText(aNews.getTitle());
        newsBrief.setText(aNews.getBrief());
        newsTime.setText(aNews.getDate());
        newsResource.setText(aNews.getResource());

        newsResource.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, NewsOfAGroupActivity.class);
                intent.putExtra("resourceName",newsResource.getText());
                context.startActivity(intent);
            }
        });

        newsImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, WebView.class);
                intent.putExtra("url",aNews.getDescriptionLink());
                intent.putExtra("newsTitle",newsTitle.getText());
                context.startActivity(intent);
            }
        });

        newsTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, WebView.class);
                intent.putExtra("url",aNews.getDescriptionLink());
                intent.putExtra("newsTitle",newsTitle.getText());
                context.startActivity(intent);
            }
        });

        newsBrief.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, WebView.class);
                intent.putExtra("url",aNews.getDescriptionLink());
                intent.putExtra("newsTitle",newsTitle.getText());
                context.startActivity(intent);
            }
        });

    }
}
