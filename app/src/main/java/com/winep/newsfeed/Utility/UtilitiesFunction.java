package com.winep.newsfeed.Utility;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.winep.newsfeed.Presenter.Observer.ObserverConnectionInternetOK;

/**
 * Created by ShaisteS on 6/15/2016.
 */
public class UtilitiesFunction {

    private static UtilitiesFunction utility = new UtilitiesFunction();


    public static UtilitiesFunction getInstance() {
        if (utility != null) {
            return utility;
        } else return new UtilitiesFunction();
    }

    public Boolean checkNetworkConnection(Context context){
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting())
            return true;
        else
            return false;

    }
}
