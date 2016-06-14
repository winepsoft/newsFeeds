package com.winep.newsfeed.Utility;

import android.content.Context;
import android.content.Intent;

/**
 * Created by ShaisteS on 6/14/2016.
 */
public class NewsToolbarManager {

    private final static NewsToolbarManager newsToolbarManager = new NewsToolbarManager();

    public static NewsToolbarManager getInstance() {
        if (newsToolbarManager != null) {
            return newsToolbarManager;
        } else return new NewsToolbarManager();
    }

    public void shareNews(Context context, String content){
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, content);
        sendIntent.setType("text/plain");
        context.startActivity(sendIntent);
    }
}
