package com.winep.newsfeed.Presenter;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.widget.Toast;

import com.winep.newsfeed.Adapter.NewsAdapter;
import com.winep.newsfeed.DataModel.News;
import com.winep.newsfeed.R;
import com.winep.newsfeed.Utility.DividerItemDecorationRecyclerView;

import java.util.ArrayList;

/**
 * Created by ShaisteS on 6/11/2016.
 */
public class NewsOfAGroupActivity extends Activity {

    private RecyclerView newsOfAGroupRecycler;
    private TextView titleOfGroup;
    private Context context;
    private String resourceName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_of_a_group);
        context=this;
        resourceName=getIntent().getExtras().getString("resourceName");
        newsOfAGroupRecycler = (RecyclerView) findViewById(R.id.newsOfAGroupRecycler);
        titleOfGroup=(TextView)findViewById(R.id.title);


        titleOfGroup.setText(resourceName);

        ArrayList<News> listNews = new ArrayList<News>();
        listNews = createNews(100);
        NewsAdapter adapter = new NewsAdapter(context,listNews);

        newsOfAGroupRecycler.setLayoutManager(new LinearLayoutManager(this));
        newsOfAGroupRecycler.addItemDecoration(new DividerItemDecorationRecyclerView(this, LinearLayoutManager.VERTICAL));
        newsOfAGroupRecycler.setAdapter(adapter);
    }


    private ArrayList<News> createNews(int newsNumber){
        ArrayList<News> listNews =new ArrayList<News>();
        News aNews=new News("عنوان خبر",
                "خلاصه ای از خبر",
                "http://iran8b.com./imageLink",
                "ایرنا",
                "1395-03-22 18:05",
                "http://sanatdaily.ir/shownews&%20%D8%A8%D9%86%DA%AF%D8%A7%D9%87%20%D8%AF%D8%A7%D8%B1%DB%8C%20%D8%A8%D8%A7%D9%86%DA%A9%20%D9%87%D8%A7%20%D8%A8%D8%B2%D8%B1%DA%AF%E2%80%8C%D8%AA%D8%B1%DB%8C%D9%86%20%D9%85%D8%A7%D9%86%D8%B9...&32680");
        for (int i=0;i<newsNumber;i++){
            listNews.add(aNews);
        }
        return listNews;
    }
}
