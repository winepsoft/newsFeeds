package com.winep.newsfeed.Presenter;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.h6ah4i.android.widget.advrecyclerview.draggable.RecyclerViewDragDropManager;
import com.winep.newsfeed.Adapter.DragRecyclerItemAdapter;
import com.winep.newsfeed.DataModel.NewsGroup;
import com.winep.newsfeed.R;
import com.winep.newsfeed.Utility.DividerItemDecorationRecyclerView;

import java.util.ArrayList;

/**
 * Created by ShaisteS on 6/14/2016.
 */
public class SettingsActivity extends Activity {

    private String pageName;
    private RecyclerView recyclerView;
    private TextView txtPageName;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewSettings);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecorationRecyclerView(this, LinearLayoutManager.VERTICAL));
        txtPageName=(TextView)findViewById(R.id.title);
        pageName=getIntent().getStringExtra("pageName");
        txtPageName.setText(pageName);

        // Setup D&D feature and RecyclerView
        RecyclerViewDragDropManager dragMgr = new RecyclerViewDragDropManager();

        dragMgr.setInitiateOnMove(false);
        dragMgr.setInitiateOnLongPress(true);

        ArrayList<NewsGroup> listNewsGroups = new ArrayList<NewsGroup>();
        for (int i = 0; i < 5; i++) {
            NewsGroup aNewsGroup=new NewsGroup("گروه خبری شماره "+(i+1),i);
            listNewsGroups.add(aNewsGroup);
        }
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(dragMgr.createWrappedAdapter(new DragRecyclerItemAdapter(listNewsGroups)));
        dragMgr.attachRecyclerView(recyclerView);
    }

}
