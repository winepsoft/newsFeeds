package com.winep.newsfeed.DataModel;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by ShaisteS on 6/14/2016.
 */
public class DataBaseHandler extends SQLiteOpenHelper {

    private static DataBaseHandler sInstance;
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "newsFeed";
    private final String TABLE_NEWS_GROUP = "tblNewsGroup";
    private final String TABLE_NEWS="tblNews";

    private final String NewsGroup_Column_Primary_Id="id";
    private final String NewsGroup_Column_id="newsGroupId";
    private final String NewsGroup_Column_Title="newsGroupTitle";
    private final String NewsGroup_Column_Priority="newsGroupPriority";

    private final String News_Column_Primary_Id="id";
    private final String News_Column_id="newsId";
    private final String News_Column_Title="newsTitle";
    private final String News_Column_News_Link_In_Site="newsLink";
     private final String News_Column_BookMark="newsBookMarkStatus";
    private final String News_Column_Like="newsLikeStatus";
    private final String News_Column_Seen="newsSeenStatus";


    public static synchronized DataBaseHandler getInstance(Context context) {

        if (sInstance == null) {
            sInstance = new DataBaseHandler(context.getApplicationContext());
        }
        return sInstance;
    }


    private DataBaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table " + TABLE_NEWS_GROUP +
                "(" +
                NewsGroup_Column_Primary_Id + " Integer primary key AUTOINCREMENT," +
                NewsGroup_Column_id + " Integer," +
                NewsGroup_Column_Title + " text," +
                NewsGroup_Column_Priority + " Integer)");
        Log.v("create", "Create News Group Table");

        db.execSQL("create table " + TABLE_NEWS +
                "(" +
                News_Column_Primary_Id + " Integer primary key AUTOINCREMENT," +
                News_Column_id + " Integer," +
                News_Column_Title + " text," +
                News_Column_News_Link_In_Site+" text,"+
                News_Column_BookMark+" Integer,"+
                News_Column_Like + " Integer,"+
                News_Column_Seen + " Integer)");
        Log.v("create", "Create News Table");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
