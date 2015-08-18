package arnold.eureka_mobile.Activity;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import arnold.eureka_mobile.Entity.User;
import arnold.eureka_mobile.R;

public class ProfileActivity extends AppCompatActivity {

    private static final String TAG = "arnold/ProfileAct";
    private SharedPreferences sharedPref;
    private SharedPreferences.Editor editor;
    private Gson gson;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

//        initialize
        gson = new GsonBuilder().create();
        sharedPref = getSharedPreferences(getString(R.string.app_key), MODE_PRIVATE);
        editor = sharedPref.edit();

        user = gson.fromJson(sharedPref.getString("user", null), User.class);

        setTextViews(); //set text views
    }

    //    TODO: Fill up appropriate textviews
    public void setTextViews(){
        TextView name = (TextView) findViewById(R.id.content_name);
        TextView userID = (TextView) findViewById(R.id.content_userID);
        TextView dateJoined = (TextView) findViewById(R.id.content_dateJoined);
        TextView contactNum = (TextView) findViewById(R.id.content_contactNo);

        name.setText(user.getName());
    }

}
