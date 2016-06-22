package com.winep.newsfeed.Presenter;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.h6ah4i.android.widget.advrecyclerview.draggable.RecyclerViewDragDropManager;
import com.winep.newsfeed.Adapter.DragRecyclerItemAdapter;
import com.winep.newsfeed.Adapter.NewsGroupAddAdapter;
import com.winep.newsfeed.DataModel.NewsGroup;
import com.winep.newsfeed.Handler.ServerConnectionHandler;
import com.winep.newsfeed.Presenter.Observer.ObserverAddNewsGroup;
import com.winep.newsfeed.Presenter.Observer.ObserverAddNewsGroupListener;
import com.winep.newsfeed.Presenter.Observer.ObserverChangeNumberOfNewsInSettings;
import com.winep.newsfeed.Presenter.Observer.ObserverRemoveNewsGroup;
import com.winep.newsfeed.Presenter.Observer.ObserverRemoveNewsGroupListener;
import com.winep.newsfeed.R;
import com.winep.newsfeed.Utility.Configuration;
import com.winep.newsfeed.Utility.DividerItemDecorationRecyclerView;

import java.util.ArrayList;

/**
 * Created by ShaisteS on 6/14/2016.
 */
public class SettingsActivity extends Activity {

    private String pageName;
    private RecyclerView removeNewsGroupRecyclerView;
    private RecyclerView addNewsGroupRecyclerView;
    private TextView txtPageName;
    private Spinner spinnerNumberOfNewsGroup;
    private Context context;
    private RecyclerViewDragDropManager dragMgr;
    private DragRecyclerItemAdapter dragRecyclerItemAdapter;
    private SharedPreferences sharedPreferencesNumberNews;
    private int numberOfNews;
    private int defaultNumberOfNews;
    private ServerConnectionHandler serverConnectionHandler;
    private  ArrayList<NewsGroup> favoriteNewsGroupsList;
    private ArrayList<NewsGroup> noFavoriteNewsGroupsList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);
        context=this;
        serverConnectionHandler=ServerConnectionHandler.getInstance(context);
        defaultNumberOfNews = Configuration.getConfig().defaultNumberOfNewsInHome;

        removeNewsGroupRecyclerView = (RecyclerView) findViewById(R.id.recyclerViewSettings);
        removeNewsGroupRecyclerView.addItemDecoration(new DividerItemDecorationRecyclerView(this, LinearLayoutManager.VERTICAL));
        addNewsGroupRecyclerView= (RecyclerView) findViewById(R.id.recyclerViewSettings_add);
        addNewsGroupRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        addNewsGroupRecyclerView.addItemDecoration(new DividerItemDecorationRecyclerView(this, LinearLayoutManager.VERTICAL));

        txtPageName=(TextView)findViewById(R.id.title);
        pageName=getIntent().getStringExtra("pageName");
        txtPageName.setText(pageName);

        spinnerNumberOfNewsGroup=(Spinner)findViewById(R.id.spinner_settings_number_of_news);
        ArrayList<String> spinnerContent=new ArrayList<String>();
        for (int i = defaultNumberOfNews; i <= 10; i++)
            spinnerContent.add(String.valueOf(i));
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,R.layout.settings_spinner_item, spinnerContent);
        spinnerNumberOfNewsGroup.setAdapter(adapter);
        SharedPreferences pref = getApplicationContext().getSharedPreferences(Configuration.getConfig().sharedPerformanceNumberNewsName, MODE_PRIVATE);
        final SharedPreferences.Editor editor = pref.edit();
        numberOfNews = pref.getInt(Configuration.getConfig().sharedPerformanceNumberNewsName, defaultNumberOfNews);
        spinnerNumberOfNewsGroup.setSelection(numberOfNews - defaultNumberOfNews);
        spinnerNumberOfNewsGroup.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                editor.putInt(Configuration.getConfig().sharedPerformanceNumberNewsName, position + defaultNumberOfNews);
                editor.commit();
                ObserverChangeNumberOfNewsInSettings.setChangeNumberNewsSettings(true);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        // Setup D&D feature and RecyclerView for remove and sort news goup
        favoriteNewsGroupsList=serverConnectionHandler.getFavoriteNewsGroupFromDataBase();
        dragMgr = new RecyclerViewDragDropManager();
        dragMgr.setInitiateOnMove(false);
        dragMgr.setInitiateOnLongPress(true);
        removeNewsGroupRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        dragRecyclerItemAdapter=new DragRecyclerItemAdapter(context,favoriteNewsGroupsList);
        removeNewsGroupRecyclerView.setAdapter(dragMgr.createWrappedAdapter(dragRecyclerItemAdapter));
        dragMgr.attachRecyclerView(removeNewsGroupRecyclerView);

        //setup recycler view for add news group
        noFavoriteNewsGroupsList=serverConnectionHandler.getNoFavoriteNewsGroupFromDataBase();
        final NewsGroupAddAdapter addAdapter=new NewsGroupAddAdapter(context,noFavoriteNewsGroupsList);
        addNewsGroupRecyclerView.setAdapter(addAdapter);

        ObserverAddNewsGroup.changeAddNewsGroupListener(new ObserverAddNewsGroupListener() {
            @Override
            public void addNewsGroup() {
                int newsGroupId= ObserverAddNewsGroup.getAddNewsGroup();
                NewsGroup aNewsGroup=serverConnectionHandler.getANewsGroup(newsGroupId);
                dragRecyclerItemAdapter.addItem(aNewsGroup);
                for (int i = 0; i< noFavoriteNewsGroupsList.size(); i++){
                    if (noFavoriteNewsGroupsList.get(i).getNewsGroupId()==aNewsGroup.getNewsGroupId())
                        addAdapter.removeANewsGroup(noFavoriteNewsGroupsList.get(i));
                }
                serverConnectionHandler.updateUserFavoriteANewsGroup(newsGroupId,1);
                serverConnectionHandler.updatePriorityOFANewsGroup(newsGroupId,dragRecyclerItemAdapter.getItemCount());
            }
        });

        ObserverRemoveNewsGroup.changeRemoveNewsGroupListener(new ObserverRemoveNewsGroupListener() {
            @Override
            public void removeNewsGroup() {
                int newsGroupId= ObserverRemoveNewsGroup.getRemoveNewsGroup();
                NewsGroup aNewsGroup=serverConnectionHandler.getANewsGroup(newsGroupId);
                addAdapter.addItem(aNewsGroup);
                for (int i = 0; i< favoriteNewsGroupsList.size(); i++){
                    if (favoriteNewsGroupsList.get(i).getNewsGroupId()==aNewsGroup.getNewsGroupId())
                        dragRecyclerItemAdapter.removeItem(favoriteNewsGroupsList.get(i));
                }
                serverConnectionHandler.updateUserFavoriteANewsGroup(newsGroupId,0);
                serverConnectionHandler.updatePriorityOfANewsGroupWhenNoFavorite(newsGroupId,aNewsGroup.getPriority());


            }
        });
    }

    @Override
    public void onBackPressed() {
        ObserverChangeNumberOfNewsInSettings.setChangeNumberNewsSettings(true);
        finish();
    }

}
