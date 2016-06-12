package com.winep.newsfeed.Adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.bignerdranch.expandablerecyclerview.ViewHolder.ParentViewHolder;
import com.winep.newsfeed.DataModel.NewsGroup;
import com.winep.newsfeed.R;

/**
 * Created by ShaisteS on 6/11/2016.
 */
public class NewsGroupParentHolder extends ParentViewHolder {

    public TextView newsGroupTitle;

    public NewsGroupParentHolder(View itemView) {
        super(itemView);
        newsGroupTitle = (TextView) itemView.findViewById(R.id.newsGroupTitle);
    }

    public void bind(Context context,NewsGroup newsGroup){
        newsGroupTitle.setText(newsGroup.getTitle());
    }
}
