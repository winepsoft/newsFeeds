package com.winep.newsfeed.Presenter.Observer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ShaisteS on 6/14/2016.
 */
public class ObserverRemoveNewsGroup {

    private static int removeNewsGroup;
    private final static List<ObserverRemoveNewsGroupListener> changeRemoveNewsGroupStatus =new ArrayList<>();

    public static int getRemoveNewsGroup() {
        return removeNewsGroup;
    }
    public static void setRemoveNewsGroup(int removeNewsGroup) {
        ObserverRemoveNewsGroup.removeNewsGroup = removeNewsGroup;
        for (ObserverRemoveNewsGroupListener statusAdd : changeRemoveNewsGroupStatus) {
            statusAdd.removeNewsGroup();
        }
    }
    public static void changeRemoveNewsGroupListener(ObserverRemoveNewsGroupListener statusRemove){
        changeRemoveNewsGroupStatus.add(statusRemove);
    }
}
