package com.winep.newsfeed.Presenter.Observer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ShaisteS on 6/20/2016.
 */
public class ObserverChangeNumberOfNewsInSettings {

    private static Boolean changeNumberNewsSettings;
    private final static List<ObserverChangeNumberOfNewsInSettingsListener> changeNumberOfNewsStatus =new ArrayList<>();

    public static Boolean getChangeNumberNewsSettings() {
        return changeNumberNewsSettings;
    }
    public static void setChangeNumberNewsSettings(Boolean changeNumberNewsSettings) {
        ObserverChangeNumberOfNewsInSettings.changeNumberNewsSettings = changeNumberNewsSettings;
        for (ObserverChangeNumberOfNewsInSettingsListener status : changeNumberOfNewsStatus) {
            status.changeNumberOfNewsInSettingsPage();
        }
    }
    public static void changeNumberOfNewsInSettingsListener(ObserverChangeNumberOfNewsInSettingsListener status){
        changeNumberOfNewsStatus.add(status);
    }

}
