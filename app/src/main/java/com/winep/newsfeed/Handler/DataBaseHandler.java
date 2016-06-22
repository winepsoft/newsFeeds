package com.winep.newsfeed.Handler;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.winep.newsfeed.DataModel.NewsGroup;

import java.util.ArrayList;

/**
 * Created by ShaisteS on 6/14/2016.
 */
public class DataBaseHandler extends SQLiteOpenHelper {

    private static DataBaseHandler sInstance;
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "newsFeed";
    private final String TABLE_NEWS_GROUP = "tblNewsGroup";
    private final String TABLE_NEWS = "tblNews";

    private final String NewsGroup_Column_Primary_Id = "id";
    private final String NewsGroup_Column_id = "newsGroupId";
    private final String NewsGroup_Column_Title = "newsGroupTitle";
    private final String NewsGroup_Column_Priority = "newsGroupPriority";
    private final String NewsGroup_Column_User_Favorite = "isUserFavorite";

    private final String News_Column_Primary_Id = "id";
    private final String News_Column_id = "newsId";
    private final String News_Column_Title = "newsTitle";
    private final String News_Column_News_Link_In_Site = "newsLink";
    private final String News_Column_BookMark = "newsBookMarkStatus";
    private final String News_Column_Like = "newsLikeStatus";
    private final String News_Column_Seen = "newsSeenStatus";


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
                NewsGroup_Column_Priority + " Integer," +
                NewsGroup_Column_User_Favorite + " Integer)");
        Log.v("create", "Create News Group Table");

        db.execSQL("create table " + TABLE_NEWS +
                "(" +
                News_Column_Primary_Id + " Integer primary key AUTOINCREMENT," +
                News_Column_id + " Integer," +
                News_Column_Title + " text," +
                News_Column_News_Link_In_Site + " text," +
                News_Column_BookMark + " Integer," +
                News_Column_Like + " Integer," +
                News_Column_Seen + " Integer)");
        Log.v("create", "Create News Table");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public Boolean existANewsGroup(int newsGroupId) {
        String query = "select " + NewsGroup_Column_id +
                " from " + TABLE_NEWS_GROUP + "" +
                " where " + NewsGroup_Column_id + "=" + newsGroupId;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor rs = db.rawQuery(query, null);
        if (rs.moveToFirst()) {
            //Exist Product
            rs.close();
            return true;
        } else {
            //Not Exist
            rs.close();
            return false;
        }
    }

    public int numberOfNewsGroup() {
        String query = "select * from " + TABLE_NEWS_GROUP;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor rs = db.rawQuery(query, null);
        int counterProductShop = rs.getCount();
        rs.close();
        return counterProductShop;
    }

    public void insertNewsGroup(NewsGroup aNewsGroup) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(TABLE_NEWS_GROUP, null, addFieldToUserGroupTable(aNewsGroup));
        Log.v("insert", "insert A NewsGroup into Table");

    }

    private ContentValues addFieldToUserGroupTable(NewsGroup aNewsGroup) {
        ContentValues values = new ContentValues();
        values.put(NewsGroup_Column_id, aNewsGroup.getNewsGroupId());
        values.put(NewsGroup_Column_Title, aNewsGroup.getTitle());
        values.put(NewsGroup_Column_Priority, aNewsGroup.getPriority());
        values.put(NewsGroup_Column_User_Favorite, aNewsGroup.getIsUserFavorite());
        return values;
    }
    private NewsGroup createANewsGroupFromCursor(Cursor rs) {
        NewsGroup aNewsGroup = new NewsGroup();
        aNewsGroup.setNewsGroupId(rs.getInt(rs.getColumnIndex(NewsGroup_Column_id)));
        aNewsGroup.setTitle(rs.getString(rs.getColumnIndex(NewsGroup_Column_Title)));
        aNewsGroup.setPriority(rs.getInt(rs.getColumnIndex(NewsGroup_Column_Priority)));
        aNewsGroup.setIsUserFavorite(rs.getInt(rs.getColumnIndex(NewsGroup_Column_User_Favorite)));
        return aNewsGroup;
    }
    public ArrayList<NewsGroup> selectAllNewsGroup(){
        String query = "select * from " + TABLE_NEWS_GROUP ;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor rs = db.rawQuery(query, null);
        ArrayList<NewsGroup> newsGroupList = new ArrayList<NewsGroup>();
        if (rs != null)
            if (rs.moveToFirst()) {
                do {

                    newsGroupList.add(createANewsGroupFromCursor(rs));
                }
                while (rs.moveToNext());

            }
        return newsGroupList;

    }

    public NewsGroup selectANewsGroup(int newsGroupId){
        String query = "select * from " + TABLE_NEWS_GROUP+
                " where "+NewsGroup_Column_id+" = "+newsGroupId;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor rs = db.rawQuery(query, null);
        NewsGroup aNewsGroup=new NewsGroup();
        if (rs != null)
            if (rs.moveToFirst()) {
                aNewsGroup=createANewsGroupFromCursor(rs);
            }
        return aNewsGroup;

    }

    public ArrayList<NewsGroup> selectUserFavoriteNewsGroup() {
        String query = "select * from " + TABLE_NEWS_GROUP +
                " where " + NewsGroup_Column_User_Favorite + "= 1"+
                " order by "+NewsGroup_Column_Priority+" ASC";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor rs = db.rawQuery(query, null);
        ArrayList<NewsGroup> newsGroupList = new ArrayList<NewsGroup>();
        if (rs != null)
            if (rs.moveToFirst()) {
                do {

                    newsGroupList.add(createANewsGroupFromCursor(rs));
                }
                while (rs.moveToNext());

            }
        return newsGroupList;
    }

    public ArrayList<NewsGroup> selectNoUserFavoriteNewsGroup() {
        String query = "select * from " + TABLE_NEWS_GROUP +
                " where " + NewsGroup_Column_User_Favorite + "=0";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor rs = db.rawQuery(query, null);
        ArrayList<NewsGroup> newsGroupList = new ArrayList<NewsGroup>();
        if (rs != null)
            if (rs.moveToFirst()) {
                do {

                    newsGroupList.add(createANewsGroupFromCursor(rs));
                }
                while (rs.moveToNext());

            }
        return newsGroupList;
    }

    public ArrayList<NewsGroup> selectANewsGroupBetweenTwoPriority(int lastPriority, int newPriority) {
        String query = "select * from " + TABLE_NEWS_GROUP +
                " where " + NewsGroup_Column_Priority + " Between "+lastPriority+" and  "+newPriority;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor rs = db.rawQuery(query, null);
        ArrayList<NewsGroup> newsGroupList = new ArrayList<NewsGroup>();
        if (rs != null)
            if (rs.moveToFirst()) {
                do {

                    newsGroupList.add(createANewsGroupFromCursor(rs));
                }
                while (rs.moveToNext());

            }
        return newsGroupList;
    }

    public ArrayList<NewsGroup> selectANewsLargerThenAPriority(int priority) {
        String query = "select * from " + TABLE_NEWS_GROUP +
                " where " + NewsGroup_Column_Priority + " > "+priority;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor rs = db.rawQuery(query, null);
        ArrayList<NewsGroup> newsGroupList = new ArrayList<NewsGroup>();
        if (rs != null)
            if (rs.moveToFirst()) {
                do {

                    newsGroupList.add(createANewsGroupFromCursor(rs));
                }
                while (rs.moveToNext());

            }
        return newsGroupList;
    }

    public void updatePriorityANewsGroup(int newsGroupId,int newPriority) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(NewsGroup_Column_Priority,newPriority);
        db.update(TABLE_NEWS_GROUP, values,
                NewsGroup_Column_id + "=" + newsGroupId, null);
        Log.v("update", "Update priority of a news group");
    }

    public void updateUserFavoriteANewsGroup(int newsGroupId,int newUserFavorite) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(NewsGroup_Column_User_Favorite,newUserFavorite);
        db.update(TABLE_NEWS_GROUP, values,
                NewsGroup_Column_id + "=" + newsGroupId, null);
        Log.v("update", "Update user favorite a news group");
    }
}
