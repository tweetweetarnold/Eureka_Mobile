package arnold.eureka_mobile.Activity;

import android.app.AlertDialog;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
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
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import arnold.eureka_mobile.Adapter.HomepageTabsPagerAdapter;
import arnold.eureka_mobile.Connection.NetworkSingleton;
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
    String[] tabList = new String[]{"Tab1", "Food", "Tab3"}; //items in tabs

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

        Toast.makeText(HomepageActivity.this, "Welcome user!", Toast.LENGTH_SHORT).show();


    }

//    TODO: *** CHRIS *** Request sample code start
    public void doButton3(View view){
        final TextView tv = (TextView) findViewById(R.id.sample_text);
        String url = "https://maps.googleapis.com/maps/api/geocode/json?address=1600+Amphitheatre+Parkway,+Mountain+View,+CA";

        StringRequest request = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                tv.setText(s);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.e(TAG, "Error message");
            }
        });
        NetworkSingleton.getInstance(getApplicationContext()).addToRequestQueue(request);
    }
    //    TODO: *** CHRIS *** Request sample code end

    public void doButton2(View view){
        Toast.makeText(this, "Notification testing", Toast.LENGTH_SHORT).show();

        NotificationCompat.Builder notBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.rsz_om_nom_waving)
                .setContentTitle("Test notification from Eureka Mobile")
                .setContentText("Test notification sample text");

        // Creates an explicit intent for an Activity in your app
        Intent resultIntent = new Intent(this, MapActivity.class);

//        This stack builder object will contain an artificial back stack for the started Activity.
//        This ensures that navigating backward from the Activity leads out of your application to the Home Screen
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
//        Add the back stack for the Intent (but not the Intent itself)
        stackBuilder.addParentStack(MapActivity.class);
//        Add the Intent that starts the Actvity to the top of the stack
        stackBuilder.addNextIntent(resultIntent);
        PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);

        notBuilder.setContentIntent(resultPendingIntent);
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
//        mId allows you to update the notification later on
        notificationManager.notify(0, notBuilder.build());

    }

//    load resources for action bar tabs view
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

//    load resources to enable swipe navigation
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

//    load resources to enable side drawer
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
//        for setting menu icon in action bar in to left hand corner
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

//    to process logout
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
