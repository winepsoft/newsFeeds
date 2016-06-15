package com.winep.newsfeed.Presenter;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.winep.newsfeed.Presenter.Observer.ObserverConnectionInternetOK;
import com.winep.newsfeed.Presenter.Observer.ObserverConnectionInternetOKListener;
import com.winep.newsfeed.R;
import com.winep.newsfeed.Utility.Configuration;
import com.winep.newsfeed.Utility.UtilitiesFunction;

/**
 * Created by ShaisteS on 5/29/2016.
 */
public class WebView extends Activity{

    private android.webkit.WebView webView;
    private TextView txtNewsTitle;
    private TextView txtCheckNetworkConnectionMessage;
    private String url;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.web_view);
        context=this;
        webView = (android.webkit.WebView) findViewById(R.id.web_view);
        txtNewsTitle =(TextView) findViewById(R.id.title);
        txtCheckNetworkConnectionMessage=(TextView)findViewById(R.id.txt_checkNetwork_message);
        url= getIntent().getStringExtra("url");
        String newsTitle=getIntent().getStringExtra("newsTitle");
        txtNewsTitle.setText(newsTitle);

        Boolean checkConnectionStatus= UtilitiesFunction.getInstance().checkNetworkConnection(context);
        if(checkConnectionStatus)
            showWebView();
        else
            hideWebView();

        ObserverConnectionInternetOK.ObserverConnectionInternetOKListener(new ObserverConnectionInternetOKListener() {
            @Override
            public void connectionOK() {
                showWebView();
            }
        });
    }

    private void showWebView(){
        txtCheckNetworkConnectionMessage.setVisibility(View.GONE);
        webView.setVisibility(View.VISIBLE);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(url);
    }

    private void hideWebView(){
        webView.setVisibility(View.GONE);
        txtCheckNetworkConnectionMessage.setVisibility(View.VISIBLE);
    }

    @Override
    public void onBackPressed(){
        if(webView.canGoBack()) {
            webView.goBack();
        } else {
            super.onBackPressed();
        }
    }
}