package arnold.eureka_mobile.Activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import arnold.eureka_mobile.Connection.NetworkSingleton;
import arnold.eureka_mobile.Model.User;
import arnold.eureka_mobile.R;

public class ProfileActivity extends AppCompatActivity {

    private static final String TAG = "arnold/ProfileAct";
    private SharedPreferences sharedPref;
    private SharedPreferences.Editor editor;
    private NetworkSingleton network;
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
        network = NetworkSingleton.getInstance(getApplicationContext());

        user = gson.fromJson(sharedPref.getString("user", null), User.class);

        setTextViews(); //set text views
        getJson();
    }

    //    TODO: Fill up appropriate textviews
    public void setTextViews(){
        TextView name = (TextView) findViewById(R.id.content_name);
        TextView userID = (TextView) findViewById(R.id.content_userID);
        TextView dateJoined = (TextView) findViewById(R.id.content_dateJoined);
        TextView contactNum = (TextView) findViewById(R.id.content_contactNo);

        name.setText(user.getName());
    }

    public void getJson(){
        String url = "https://maps.googleapis.com/maps/api/geocode/json?address=1600+Amphitheatre+Parkway,+Mountain+View,+CA";
        final TextView textView = (TextView) findViewById(R.id.sample_text);
        StringRequest request = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                textView.setText(s);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                textView.setText("Error response");
            }
        });
        network.addToRequestQueue(request);
    }

}
