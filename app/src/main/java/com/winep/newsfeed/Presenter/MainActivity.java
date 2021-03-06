package com.winep.newsfeed.Presenter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.winep.newsfeed.Adapter.NewsAdapter;
import com.winep.newsfeed.DataModel.News;
import com.winep.newsfeed.DataModel.NewsGroup;
import com.winep.newsfeed.Handler.ServerConnectionHandler;
import com.winep.newsfeed.Presenter.Observer.ObserverChangeNumberOfNewsInSettings;
import com.winep.newsfeed.Presenter.Observer.ObserverChangeNumberOfNewsInSettingsListener;
import com.winep.newsfeed.R;
import com.winep.newsfeed.Utility.Configuration;
import com.winep.newsfeed.Utility.DividerItemDecorationRecyclerView;
import com.winep.newsfeed.Utility.UtilitiesFunction;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private ServerConnectionHandler serverConnectionHandler;
    private Context context;
    private LinearLayout mainLayout;
    private SharedPreferences sharedPreferencesFirstInstallApp;
    private Boolean firstInstallStatus;
    private NavigationView navigationView;
    private ArrayList<NewsGroup> newsGroupList;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        context=this;
        serverConnectionHandler=ServerConnectionHandler.getInstance(context);
        Configuration.getConfig().connectionStatus= UtilitiesFunction.getInstance().checkNetworkConnection(context);

        SharedPreferences pref = getApplicationContext().getSharedPreferences(Configuration.getConfig().sharedPerformanceFirstInstallApp, MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        firstInstallStatus = pref.getBoolean(Configuration.getConfig().sharedPerformanceFirstInstallApp, Configuration.getConfig().defaultFirstInstallStatus);
        newsGroupList =new ArrayList<>();
        if (firstInstallStatus){
            newsGroupList =serverConnectionHandler.getNewsGroupFromServer();
            serverConnectionHandler.addNewsGroupInDataBaseForFirst(newsGroupList);
            editor.putBoolean(Configuration.getConfig().sharedPerformanceFirstInstallApp,false);
            editor.commit();

        }
        else{
            newsGroupList=serverConnectionHandler.getFavoriteNewsGroupFromDataBase();
        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        createNavigationDrawerMenu(newsGroupList);

        mainLayout=(LinearLayout)findViewById(R.id.mainLayout);
        for (int i = 0; i< newsGroupList.size(); i++)
            mainLayout.addView(createViewNewsGroup(newsGroupList.get(i),getNewsOfANewsGroup(newsGroupList.get(i).getNewsGroupId())));
        ObserverChangeNumberOfNewsInSettings.changeNumberOfNewsInSettingsListener(new ObserverChangeNumberOfNewsInSettingsListener() {
            @Override
            public void changeNumberOfNewsInSettingsPage() {
                recreate();
            }
        });

    }

    private LinearLayout createViewNewsGroup(NewsGroup aNewsGroup,ArrayList<News> newsList){
        LinearLayout layout = new LinearLayout(this);
        layout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        layout.setOrientation(LinearLayout.VERTICAL);

        View newsGroupLayout = getLayoutInflater().inflate(R.layout.news_group_item_for_home,layout, false);
        RecyclerView newsRecyclerView=(RecyclerView)newsGroupLayout.findViewById(R.id.newsGroupRecyclerView);
        Button btnNewsGroupTitle=(Button)newsGroupLayout.findViewById(R.id.btn_news_group_title);

        newsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        newsRecyclerView.addItemDecoration(new DividerItemDecorationRecyclerView(this, LinearLayoutManager.VERTICAL));
        NewsAdapter adapter = new NewsAdapter(context,newsList);
        newsRecyclerView.setAdapter(adapter);

        final String finalNewsGroupTitle = aNewsGroup.getTitle();
        btnNewsGroupTitle.setText(aNewsGroup.getTitle());
        btnNewsGroupTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, NewsOfAGroupActivity.class);
                intent.putExtra("resourceName", finalNewsGroupTitle);
                context.startActivity(intent);
            }
        });

        layout.addView(newsGroupLayout);
        return layout;
    }

    private int getNumberOfNewsInHome(){
        SharedPreferences pref = getApplicationContext().getSharedPreferences(Configuration.getConfig().sharedPerformanceNumberNewsName, MODE_PRIVATE);
        final SharedPreferences.Editor editor = pref.edit();
        return pref.getInt(Configuration.getConfig().sharedPerformanceNumberNewsName,Configuration.getConfig().defaultNumberOfNewsInHome);
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

    private ArrayList<News> getNewsOfANewsGroup(int newsGroupId){
        ArrayList<News> listNews = new ArrayList<News>();
        listNews = createNews(getNumberOfNewsInHome());
        return listNews;
    }

    private void createNavigationDrawerMenu(ArrayList<NewsGroup> newsGroupList){
        Menu m = navigationView.getMenu() ;
        SubMenu topChannelMenu = m.addSubMenu(R.string.newsGroup);
        for (int i=0;i<newsGroupList.size();i++){
            topChannelMenu.add(newsGroupList.get(i).getTitle());
        }
        m.add(R.string.book_mark).setIcon(R.drawable.ic_menu_gallery);
        m.add(R.string.settings).setIcon(R.drawable.ic_menu_manage);
        m.add(R.string.share).setIcon(R.drawable.ic_menu_share);
        m.add(R.string.for_us).setIcon(R.drawable.ic_menu_send);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        if (item.getTitle().equals("گروه خبری 1")){
            Intent intent=new Intent(context, NewsOfAGroupActivity.class);
            intent.putExtra("resourceName", item.getTitle());
            context.startActivity(intent);
        }
        else if (item.getTitle().equals("گروه خبری 2")){
            Intent intent=new Intent(context, NewsOfAGroupActivity.class);
            intent.putExtra("resourceName", item.getTitle());
            context.startActivity(intent);
        }
        else if (item.getTitle().equals("گروه خبری 3")){
            Intent intent=new Intent(context, NewsOfAGroupActivity.class);
            intent.putExtra("resourceName", item.getTitle());
            context.startActivity(intent);
        }
        else if (item.getTitle().equals("گروه خبری 4")){
            Intent intent=new Intent(context, NewsOfAGroupActivity.class);
            intent.putExtra("resourceName", item.getTitle());
            context.startActivity(intent);
        }
        else if (item.getTitle().equals("گروه خبری 5")){
            Intent intent=new Intent(context, NewsOfAGroupActivity.class);
            intent.putExtra("resourceName", item.getTitle());
            context.startActivity(intent);
        }
        else if (item.getTitle().equals("تنظیمات")){
            Intent intent=new Intent(context,SettingsActivity.class);
            intent.putExtra("pageName", item.getTitle());
            context.startActivity(intent);
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
