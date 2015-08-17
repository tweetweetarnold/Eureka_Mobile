package arnold.eureka_mobile.Activity;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import arnold.eureka_mobile.R;

public class CanteenActivity extends AppCompatActivity {

    private final static String TAG = "arnold/CanteenAct";
    private Gson gson;
    private SharedPreferences sharedPref;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_canteen);

//        initialize
        gson = new GsonBuilder().create();
        sharedPref = getSharedPreferences(getString(R.string.app_key), MODE_PRIVATE);
        editor = sharedPref.edit();
    }

}
