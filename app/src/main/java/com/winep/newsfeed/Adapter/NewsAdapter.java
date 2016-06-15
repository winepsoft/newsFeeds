package com.winep.newsfeed.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.winep.newsfeed.DataModel.News;
import com.winep.newsfeed.Presenter.NewsOfAGroupActivity;
import com.winep.newsfeed.Presenter.WebView;
import com.winep.newsfeed.R;
import com.winep.newsfeed.Utility.NewsToolbarManager;

import java.util.ArrayList;

/**
 * Created by ShaisteS on 6/11/2016.
 */
public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.MyViewHolder> {

    private ArrayList<News> newsList;
    private Context context;

    public NewsAdapter(Context context,ArrayList<News> newsList)
    {
        this.newsList = newsList;
        this.context=context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.news_information_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final News aNews = newsList.get(position);
        holder.newsTitle.setText(aNews.getTitle());
        holder.newsBrief.setText(aNews.getBrief());
        holder.newsTime.setText(aNews.getDate());
        holder.newsResource.setText(aNews.getResource());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, WebView.class);
                intent.putExtra("url",aNews.getDescriptionLink());
                intent.putExtra("newsTitle",holder.newsTitle.getText());
                context.startActivity(intent);
            }
        });

        holder.newsResource.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, NewsOfAGroupActivity.class);
                intent.putExtra("resourceName",holder.newsResource.getText());
                context.startActivity(intent);

            }
        });

        holder.newsImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, WebView.class);
                intent.putExtra("url",aNews.getDescriptionLink());
                intent.putExtra("newsTitle",holder.newsTitle.getText());
                context.startActivity(intent);
            }
        });

        holder.newsTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, WebView.class);
                intent.putExtra("url",aNews.getDescriptionLink());
                intent.putExtra("newsTitle",holder.newsTitle.getText());
                context.startActivity(intent);
            }
        });

        holder.newsBrief.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, WebView.class);
                intent.putExtra("url",aNews.getDescriptionLink());
                intent.putExtra("newsTitle",holder.newsTitle.getText());
                context.startActivity(intent);
            }
        });

        holder.newsShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String shareContent=aNews.getTitle()+"\n"+
                        aNews.getDescriptionLink()+"\n"+
                        aNews.getResource();
                NewsToolbarManager.getInstance().shareNews(context,shareContent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder  {

        public ImageButton newsShare;
        public ImageButton newsBookMark;
        public ImageButton newsImage;
        public TextView newsTitle;
        public TextView newsBrief;
        public TextView newsTime;
        public TextView newsResource;

        public MyViewHolder(View view) {
            super(view);
            newsShare=(ImageButton)view.findViewById(R.id.newsShare);
            newsBookMark=(ImageButton)view.findViewById(R.id.newsBookMark);
            newsImage=(ImageButton)view.findViewById(R.id.newsImage);
            newsTitle=(TextView)view.findViewById(R.id.newsTitle);
            newsBrief=(TextView)view.findViewById(R.id.newsBrief);
            newsTime=(TextView)view.findViewById(R.id.newsTime);
            newsResource=(TextView)view.findViewById(R.id.newsResource);
        }
    }
}
