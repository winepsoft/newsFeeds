package com.winep.newsfeed.Presenter.Observer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ShaisteS 13/2016.
 */
public class ObserverAddNewsGroup {

    private static int addNewsGroup;
    private final static List<ObserverAddNewsGroupListener> changeAddNewsGroupStatus =new ArrayList<>();

    public static int getAddNewsGroup() {
        return addNewsGroup;
    }
    public static void setAddNewsGroup(int addNewsGroup) {
        ObserverAddNewsGroup.addNewsGroup = addNewsGroup;
        for (ObserverAddNewsGroupListener statusAdd : changeAddNewsGroupStatus) {
            statusAdd.addNewsGroup();
        }
    }
    public static void changeAddNewsGroupListener(ObserverAddNewsGroupListener statusAdd){
        changeAddNewsGroupStatus.add(statusAdd);
    }

}
