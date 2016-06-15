package com.winep.newsfeed.Presenter.Services;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.View;

import com.winep.newsfeed.Presenter.Observer.ObserverConnectionInternetOK;
import com.winep.newsfeed.Utility.Configuration;

/**
 * Created by ShaisteS on 6/15/2016.
 */
public class CheckNetworkConnection extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getExtras() != null) {
            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo netInfo = cm.getActiveNetworkInfo();
            if (netInfo != null && netInfo.isConnectedOrConnecting()) {
                Configuration.getConfig().connectionStatus = true;
                ObserverConnectionInternetOK.setChangeConnectionStatus(true);
            }
            if (intent.getExtras().getBoolean(ConnectivityManager.EXTRA_NO_CONNECTIVITY, Boolean.FALSE)) {
                Configuration.getConfig().connectionStatus = false;
                ObserverConnectionInternetOK.setChangeConnectionStatus(false);
            }
        }
    }
}
