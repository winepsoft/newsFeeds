package com.winep.newsfeed.Handler;

import android.content.Context;

import com.winep.newsfeed.DataModel.NewsGroup;

import java.util.ArrayList;

/**
 * Created by ShaisteS on 6/20/2016.
 */
public class ServerConnectionHandler {

    private static ServerConnectionHandler serverConnectionHandlerInstance;
    private DataBaseHandler dataBaseHandler;
    private Context context;

    public static ServerConnectionHandler getInstance(Context context) {
        if (serverConnectionHandlerInstance == null) {
            serverConnectionHandlerInstance = new ServerConnectionHandler(context.getApplicationContext());
        }
        return serverConnectionHandlerInstance;
    }

    public ServerConnectionHandler(Context myContext){
        context=myContext;
        dataBaseHandler=DataBaseHandler.getInstance(context);
    }

    public Boolean existANewsGroupInDataBase(int newsGroupId){
        return  dataBaseHandler.existANewsGroup(newsGroupId);
    }

    public int getNumberOfNewsGroupInDataBase(){
        return dataBaseHandler.numberOfNewsGroup();
    }

    public ArrayList<NewsGroup> getNewsGroupFromServer(){
        ArrayList<NewsGroup> newsgroupList=new ArrayList<>();
        for (int i=0;i<10;i++){
            NewsGroup aNewsGroup=new NewsGroup();
            aNewsGroup.setTitle("گروه خبری شماره"+(i+1));
            aNewsGroup.setNewsGroupId(i+1);
            //aNewsGroup.setPriority(i+1);
            //aNewsGroup.setIsUserFavorite(1);
            newsgroupList.add(aNewsGroup);
        }
        return newsgroupList;
    }

    public ArrayList<NewsGroup> getFavoriteNewsGroupFromDataBase(){
        return dataBaseHandler.selectUserFavoriteNewsGroup();
    }

    public ArrayList<NewsGroup> getNoFavoriteNewsGroupFromDataBase(){
        return dataBaseHandler.selectNoUserFavoriteNewsGroup();
    }

    public NewsGroup getANewsGroup(int newsGroupId){
        return dataBaseHandler.selectANewsGroup(newsGroupId);
    }

    public void addNewsGroupInDataBaseForFirst(ArrayList<NewsGroup> newsGroupList){
        int priorityNumber=getNumberOfNewsGroupInDataBase()+1;
        for (int i=0;i<newsGroupList.size();i++) {
            newsGroupList.get(i).setIsUserFavorite(1);
            newsGroupList.get(i).setPriority(priorityNumber);
            dataBaseHandler.insertNewsGroup(newsGroupList.get(i));
            priorityNumber++;
        }
    }

    public void updatePriorityOfANewsGroupInFavoriteList(NewsGroup aNewsGroup, int newPriority){
        if(aNewsGroup.getPriority()<newPriority){
            ArrayList<NewsGroup> newsGroupListBetweenTwoPriority=dataBaseHandler.selectANewsGroupBetweenTwoPriority(aNewsGroup.getPriority(),newPriority);
            for (int i=0;i<newsGroupListBetweenTwoPriority.size();i++) {
                if (newsGroupListBetweenTwoPriority.get(i).getNewsGroupId()==aNewsGroup.getNewsGroupId())
                    dataBaseHandler.updatePriorityANewsGroup(newsGroupListBetweenTwoPriority.get(i).getNewsGroupId(),newPriority);
                else
                    dataBaseHandler.updatePriorityANewsGroup(newsGroupListBetweenTwoPriority.get(i).getNewsGroupId(),newsGroupListBetweenTwoPriority.get(i).getPriority()-1);
            }
        }
        if(aNewsGroup.getNewsGroupId()>newPriority){
            ArrayList<NewsGroup> newsGroupListBetweenTwoPriority=dataBaseHandler.selectANewsGroupBetweenTwoPriority(newPriority,aNewsGroup.getPriority());
            for (int i=0;i<newsGroupListBetweenTwoPriority.size();i++) {
                if (newsGroupListBetweenTwoPriority.get(i).getNewsGroupId()==aNewsGroup.getNewsGroupId())
                    dataBaseHandler.updatePriorityANewsGroup(newsGroupListBetweenTwoPriority.get(i).getNewsGroupId(),newPriority);
                else
                    dataBaseHandler.updatePriorityANewsGroup(newsGroupListBetweenTwoPriority.get(i).getNewsGroupId(),newsGroupListBetweenTwoPriority.get(i).getPriority()+1);
            }

        }
    }

    public void updatePriorityOfANewsGroupWhenNoFavorite(int newsGroup,int lastPriority){
        ArrayList<NewsGroup> newsGroupListBetweenTwoPriority=dataBaseHandler.selectANewsLargerThenAPriority(lastPriority);
        for (int i=0;i<newsGroupListBetweenTwoPriority.size();i++)
            dataBaseHandler.updatePriorityANewsGroup(newsGroupListBetweenTwoPriority.get(i).getNewsGroupId(),newsGroupListBetweenTwoPriority.get(i).getPriority()-1);

        dataBaseHandler.updatePriorityANewsGroup(newsGroup,0);

    }

    public void updatePriorityOFANewsGroup(int newsGroupId, int newPriority){
        dataBaseHandler.updatePriorityANewsGroup(newsGroupId,newPriority);
    }

    public void updateUserFavoriteANewsGroup(int newsGroupId, int newUserFavorite){
        dataBaseHandler.updateUserFavoriteANewsGroup(newsGroupId,newUserFavorite);
    }

}
