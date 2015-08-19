package arnold.eureka_mobile.Controller;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import arnold.eureka_mobile.Activity.HomepageActivity;
import arnold.eureka_mobile.Model.User;
import arnold.eureka_mobile.R;
import arnold.eureka_mobile.TestCreator;

/**
 * Created by Arnold on 8/19/2015.
 */
public class AccessController {

    private final static String TAG = "AccessControl";
    private static SharedPreferences sharedPref;
    private static SharedPreferences.Editor editor;
    private static Gson gson;
    Context context;

    public AccessController(Context context) {
        this.context = context;
        gson = new GsonBuilder().create();
        sharedPref = context.getSharedPreferences(context.getString(R.string.app_key), Context.MODE_PRIVATE);
        editor = sharedPref.edit();
    }

    public static AccessController getInstance(Context context){
        return new AccessController(context);
    }

    public boolean processLogin (String username, String password) {
//        for testing
        User testUser = TestCreator.getTestUser();

//        check if user is valid
        boolean authenticate = (username.equals(testUser.getUsername()) && password.equals(testUser.getPassword()));
        return authenticate;
    }
}
