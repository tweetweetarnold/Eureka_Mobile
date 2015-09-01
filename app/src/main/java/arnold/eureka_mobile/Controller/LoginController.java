package arnold.eureka_mobile.Controller;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Network;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.RequestFuture;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Method;
import java.util.concurrent.ExecutionException;

import arnold.eureka_mobile.Connection.NetworkSingleton;
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
    private static NetworkSingleton network;
    private static Gson gson;
    Context context;

    public LoginController(Context context) {
//        this.context = context;
        gson = new GsonBuilder().create();
        sharedPref = context.getSharedPreferences(context.getString(R.string.app_key), Context.MODE_PRIVATE);
        editor = sharedPref.edit();
        network = NetworkSingleton.getInstance(context);
    }

//    public static LoginController getInstance(Context context){
//        return new LoginController(context);
//    }

    public boolean authenticateUser (String username, String password) {
//        String url = "https://maps.googleapis.com/maps/api/geocode/json?address=1600+Amphitheatre+Parkway,+Mountain+View,+CA"; // TODO: Insert correct getToken URL
//        System.out.println("authenticate user");
//        RequestFuture<JSONObject> future = RequestFuture.newFuture();
//        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, null, future, future);
//        System.out.println("Request: " + request);
//        network.addToRequestQueue(request);
//
//        try {
//            JSONObject response = future.get();
//            if(response.getString("status").equals("OK")){
//                Log.i(TAG, "status is ok");
//            }
//        } catch (InterruptedException | ExecutionException | JSONException e) {
//            e.printStackTrace();
//        }

        String token = "EUREKA123456";
        System.out.println("TOKEN: " + token);
        editor.putString("token", token).commit();
        return true;
    }
}
