package com.winep.newsfeed.Presenter.Observer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ShaisteS on 1394/12/15.
 */
public class ObserverConnectionInternetOK {

    private static Boolean changeConnectionStatus;
    private  final static List<ObserverConnectionInternetOKListener> ChangeConnectionListener=new ArrayList<>();

    public static Boolean getChangeConnectionStatus() {
        return changeConnectionStatus;
    }
    public static void setChangeConnectionStatus( Boolean changeConnection) {
        ObserverConnectionInternetOK.changeConnectionStatus = changeConnection;
        for (ObserverConnectionInternetOKListener setConnection : ChangeConnectionListener) {
            setConnection.connectionOK();
        }
    }

    public static void ObserverConnectionInternetOKListener(ObserverConnectionInternetOKListener change){
        ChangeConnectionListener.add(change);
    }
}
