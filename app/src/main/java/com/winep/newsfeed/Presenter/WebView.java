package com.winep.newsfeed.Presenter;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.winep.newsfeed.R;

/**
 * Created by ShaisteS on 5/29/2016.
 */
public class WebView extends Activity{

    private android.webkit.WebView browser;
    private TextView txtNewsTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.web_view);
        String url= getIntent().getStringExtra("url");
        String newsTitle=getIntent().getStringExtra("newsTitle");
        browser = (android.webkit.WebView) findViewById(R.id.web_view);
        txtNewsTitle =(TextView) findViewById(R.id.title);
        txtNewsTitle.setText(newsTitle);
        WebSettings webSettings = browser.getSettings();
        webSettings.setJavaScriptEnabled(true);
        browser.setWebViewClient(new WebViewClient());
        browser.loadUrl(url);
    }

    @Override
    public void onBackPressed(){
        if(browser.canGoBack()) {
            browser.goBack();
        } else {
            super.onBackPressed();
        }
    }
}