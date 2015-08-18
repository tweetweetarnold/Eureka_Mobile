package arnold.eureka_mobile.Activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import arnold.eureka_mobile.Adapter.HomepageTabsPagerAdapter;
import arnold.eureka_mobile.R;

public class HomepageActivity extends AppCompatActivity {

    private final static String TAG = "arnold/HomepageAct";
    private Gson gson;
    private SharedPreferences sharedPref;
    private SharedPreferences.Editor editor;
    private ActionBar actionBar;

    private ViewPager viewPager;
    private DrawerLayout drawerLayout;

    String[] drawerList = new String[]{"My Profile", "Canteen", "Maps", "Logout"}; //items in drawer
    String[] tabList = new String[]{"Tab1", "Tab2", "Tab3"}; //items in tabs

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        try {
//            setting actionbar display
            actionBar = getSupportActionBar();
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_white_18dp);
        }catch(NullPointerException e){
            e.getStackTrace();
            Log.e(TAG, "Error Message: " + e.getMessage());
            Log.e(TAG, "Action bar is null");
        }

//        initialize
        gson = new GsonBuilder().create();
        sharedPref = getSharedPreferences(getString(R.string.app_key), MODE_PRIVATE);
        editor = sharedPref.edit();

        loadSwipeView(); //load swipe view
        loadActionBarTabs(); //load Action bar tabs
        loadDrawer(); //load Drawer

        viewPager.setCurrentItem(1);
    }

    public void loadActionBarTabs(){
        actionBar = getSupportActionBar();
        actionBar.setNavigationMode(android.support.v7.app.ActionBar.NAVIGATION_MODE_TABS);
        android.support.v7.app.ActionBar.TabListener tabListener = new android.support.v7.app.ActionBar.TabListener() {
            @Override
            public void onTabSelected(android.support.v7.app.ActionBar.Tab tab, android.support.v4.app.FragmentTransaction ft) {
                viewPager.setCurrentItem(tab.getPosition());
            }
            @Override
            public void onTabUnselected(android.support.v7.app.ActionBar.Tab tab, android.support.v4.app.FragmentTransaction ft) {
            }
            @Override
            public void onTabReselected(android.support.v7.app.ActionBar.Tab tab, android.support.v4.app.FragmentTransaction ft) {

            }
        };
//         Add 3 tabs, specifying the tab's text and TabListener

        for (int i = 0; i < tabList.length; i++) {
            actionBar.addTab(actionBar.newTab().setText(tabList[i]).setTabListener(tabListener));
        }
    }

    public void loadSwipeView(){
        viewPager = (ViewPager) findViewById(R.id.homepage_pager);
        viewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                actionBar.setSelectedNavigationItem(position);
            }
        });
        HomepageTabsPagerAdapter homepageTabsPagerAdapter = new HomepageTabsPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(homepageTabsPagerAdapter);
    }



    public void loadDrawer(){
        final ArrayAdapter<String> drawerAdapter = new ArrayAdapter<>(this, R.layout.drawer_item, drawerList);
        drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);

        ListView listView = (ListView)findViewById(R.id.homepage_drawer);
        listView.setAdapter(drawerAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String itemTitle = drawerAdapter.getItem(position);
                Context context = getApplicationContext();
                Intent intent = null;
                if (itemTitle.equals("Logout")) {
                    processLogout();
                } else {
                    switch (itemTitle) {
                        case "My Profile":
                            intent = new Intent(context, ProfileActivity.class);
                            break;
                        case "Canteen":
                            intent = new Intent(context, CanteenActivity.class);
                            break;
                        case "Maps":
                            intent = new Intent(context, MapActivity.class);
                            break;
                        default:
                            Toast.makeText(context, "Sorry. The page could not be started.", Toast.LENGTH_LONG).show();
                            Log.e(TAG, "Default value is run on drawer switch.");
                            break;
                    }
                    startActivity(intent);
                    drawerLayout.closeDrawers();
                }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case android.R.id.home:
                if(drawerLayout.isDrawerOpen(Gravity.LEFT)){
                    drawerLayout.closeDrawers();
                }else{
                    drawerLayout.openDrawer(Gravity.LEFT);
                }
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void processLogout(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Logout confirmation").setMessage("Leaving so soon? We will miss you");
        builder.setPositiveButton("Yes, I'm leaving!", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                editor.clear().apply();
                finish();
            }
        });
        builder.setNegativeButton("No, I'm staying!", null);
        builder.create().show();
    }

}
