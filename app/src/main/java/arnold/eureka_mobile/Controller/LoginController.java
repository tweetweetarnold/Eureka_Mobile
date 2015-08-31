package arnold.eureka_mobile.Controller;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import arnold.eureka_mobile.Entity.Employee;
import arnold.eureka_mobile.R;
import arnold.eureka_mobile.TestCreator;

/**
 * Created by Arnold on 8/19/2015.
 */
public class LoginController {

    private final static String TAG = "LoginControl";
    private static SharedPreferences sharedPref;
    private static SharedPreferences.Editor editor;
    private static Gson gson;
    Context context;

    public LoginController(Context context) {
        this.context = context;
        gson = new GsonBuilder().create();
        sharedPref = context.getSharedPreferences(context.getString(R.string.app_key), Context.MODE_PRIVATE);
        editor = sharedPref.edit();
    }

    public static LoginController getInstance(Context context){
        return new LoginController(context);
    }

    public boolean processLogin (String username, String password) {
//        for testing
        Employee testEmployee = TestCreator.getTestUser();

//        check if user is valid
        boolean authenticate = (username.equals(testEmployee.getUsername()) && password.equals(testEmployee.getPassword()));
        return authenticate;
    }
}
