package com.winep.newsfeed.Utility;

/**
 * Created by ShaisteS on 6/15/2016.
 */
public class Configuration {

    private final static Configuration config = new Configuration();

    public static Configuration getConfig() {
        if (config != null) {
            return config;
        } else return new Configuration();
    }

    public Boolean connectionStatus = false;

}
