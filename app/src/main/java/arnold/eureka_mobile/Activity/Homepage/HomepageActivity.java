package arnold.eureka_mobile.Activity.Homepage;

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
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import arnold.eureka_mobile.Activity.CanteenActivity;
import arnold.eureka_mobile.Activity.EmployeeProfileActivity;
import arnold.eureka_mobile.Activity.MapActivity;
import arnold.eureka_mobile.Adapter.HomepageTabsPagerAdapter;
import arnold.eureka_mobile.R;

public class HomepageActivity extends AppCompatActivity {

    private final static String TAG = "eureka/HomepageAct";
    private Gson gson;
    private SharedPreferences sharedPref;
    private SharedPreferences.Editor editor;
    private ActionBar actionBar;
    private ViewPager viewPager;
    private DrawerLayout drawerLayout;
    private LinearLayout drawer;

    private String[] tabList;
    private String[] drawerList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        try {
            // initialize builders
            gson = new GsonBuilder().create();
            sharedPref = getSharedPreferences(getString(R.string.app_key), MODE_PRIVATE);
            editor = sharedPref.edit();

            // load string arrays
            tabList = getResources().getStringArray(R.array.tabList);
            drawerList = getResources().getStringArray(R.array.drawerList);

            // load actionbar and set its display
            actionBar = getSupportActionBar();
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_white_18dp);

            // START: order is important here
            loadSwipeView(); //load swipe view
            loadActionBarTabs(); //load Action bar tabs
            loadDrawer(); //load Drawer
            // END: order is important here

            viewPager.setCurrentItem(1); // load the tab position

        }catch(NullPointerException e){
            Log.e(TAG, "Eureka error at HomepageActivty onCreate. Message: " + e.getMessage());
            e.getStackTrace();
        }

        Toast.makeText(this, "Welcome user!", Toast.LENGTH_SHORT).show(); // welcome toast message
    }

    // TODO: Test notification codes
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
        actionBar.setNavigationMode(android.support.v7.app.ActionBar.NAVIGATION_MODE_TABS); // set the tab type
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

//        drawer = (LinearLayout) findViewById(R.id.drawer);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
//        SimpleAdapter simpleAdapter = new SimpleAdapter(this, drawerList, drawerLayout, );

        ListView listView = (ListView) findViewById(R.id.drawer_list);
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
                            intent = new Intent(context, EmployeeProfileActivity.class);
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

    public void processLogout(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Logout confirmation").setMessage("Leaving so soon? We will miss you");
        builder.setPositiveButton("Yes, I'm leaving!", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                editor.clear().apply(); // to clear saved token and user profiles in editor
                finish();
            }
        });
        builder.setNegativeButton("No, I'm staying!", null);
        builder.create().show();
    }

}
