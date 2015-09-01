package arnold.eureka_mobile.Activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import arnold.eureka_mobile.R;

public class Homepage2Activity extends ActionBarActivity {

    private final static String TAG = "eureka/homepage2ACT";
    private SharedPreferences sharedPref;
    private SharedPreferences.Editor editor;
    private String[] drawerList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage2);

        sharedPref = getSharedPreferences(getString(R.string.app_key), MODE_PRIVATE);
        editor = sharedPref.edit();

        try {
//            setting actionbar display
            ActionBar actionBar = getSupportActionBar();
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_white_18dp);
        }catch(NullPointerException e){
            e.getStackTrace();
            Log.e(TAG, "Error Message: " + e.getMessage());
            Log.e(TAG, "Action bar is null");
        }

        loadDrawer();
    }

//    load resources to enable side drawer
    public void loadDrawer(){
        final String[] drawerList = getResources().getStringArray(R.array.drawerList);
        final ArrayAdapter<String> drawerAdapter = new ArrayAdapter<>(this, R.layout.drawer_item, drawerList);
        final DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        ListView listView = (ListView) findViewById(R.id.drawer_list);
        listView.setAdapter(drawerAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String itemTitle = drawerAdapter.getItem(position);
                Context context = getApplicationContext();
                Intent intent = null;

                if (itemTitle.equals("Logout")) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
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
                } else {
                    switch (itemTitle) {
                        case "My Profile":
                            intent = new Intent(context, ProfileActivity.class);
                            break;
                        case "Menu":
                            intent = new Intent(context, CanteenSelectorActivity.class);
                            break;
                        case "Maps":
                            intent = new Intent(context, MapActivity.class);
                            break;
                        default:
                            Toast.makeText(context, "Sorry. The page could not be started.", Toast.LENGTH_LONG).show();
                            Log.e(TAG, "Default value is run on drawer switch.");
                            break;
                    }
                    if(intent != null) {
                        startActivity(intent);
                    }
                    drawerLayout.closeDrawers();
                }
            }
        });
    }
}
