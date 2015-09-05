package arnold.eureka_mobile.Controller;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONException;
import org.json.JSONObject;

import arnold.eureka_mobile.Activity.Homepage.HomepageActivity;
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
    private final Context context;

    public LoginController(Context context) {
        this.context = context;
        gson = new GsonBuilder().create();
        sharedPref = context.getSharedPreferences(context.getString(R.string.app_key), Context.MODE_PRIVATE);
        editor = sharedPref.edit();
        network = NetworkSingleton.getInstance(context);
    }

    // TODO: TESTING METHOD
    public void processUserCredentialsTEST (String username, String password) {
        String url = "https://maps.googleapis.com/maps/api/geocode/json?address=1600+Amphitheatre+Parkway,+Mountain+View,+CA"; // TODO: Insert correct getToken URL
//        String url = "www.google.com.sg";

        JsonObjectRequest request = new JsonObjectRequest(url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                Log.i(TAG, "User validation successful. Token retrieved.");

                Employee testEmployee = TestCreator.getTestUser();
                editor.putString("user", gson.toJson(testEmployee)).commit();

                editor.putString("token", "abc123").commit();

                context.startActivity(new Intent(context, HomepageActivity.class)); // redirects user to next activity

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.e(TAG, "Eureka error at authenticateUser. Message: " + volleyError.getMessage());
                volleyError.printStackTrace();
            }
        });
        RetryPolicy retryPolicy = new DefaultRetryPolicy(5000, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        request.setRetryPolicy(retryPolicy);
        Log.d(TAG, "Processing request");
        network.addToRequestQueue(request);
    }

    public void processUserCredentials (String username, String password) {
        String url = "https://maps.googleapis.com/maps/api/geocode/json?address=1600+Amphitheatre+Parkway,+Mountain+View,+CA"; // TODO: Insert correct getToken URL
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

        JsonObjectRequest request = new JsonObjectRequest(url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                try {
                    String status = jsonObject.getString(context.getString(R.string.JSON_STATUS)); // get jsonObject status

                    if(status.equals(context.getString(R.string.JSON_STATUS_PASS))){
                        Log.i(TAG, "User validation successful. Token retrieved.");

                        // retrieves user details and load into application's SharedPreferences // TODO: Testing only
                        Employee testEmployee = TestCreator.getTestUser();
                        editor.putString("user", gson.toJson(testEmployee)).commit();

                        // sets user token to SharedPreferences
                        String token = jsonObject.getString(context.getString(R.string.JSON_MESSAGE));
                        editor.putString("token", token).commit();

                        context.startActivity(new Intent(context, HomepageActivity.class)); // redirects user to next activity
                    }else{
                        Log.e(TAG, "Invalid user credentials.");
                        runAlertDialog("Invalid credentials",
                                "Oops! Something went wrong!\nMake sure your username and password is correct.");
                    }

                } catch (JSONException e) {
                    Log.e(TAG, "Eureka error at authenticateUser. Message: " + e.getMessage());
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.e(TAG, "Eureka error at authenticateUser. Message: " + volleyError.getMessage());
                volleyError.printStackTrace();
            }
        });
        int timeout = 5000;
        RetryPolicy retryPolicy = new DefaultRetryPolicy(timeout, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        request.setRetryPolicy(retryPolicy);
        network.addToRequestQueue(request);
    }

    public void runAlertDialog(String title, String message){
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(context);
        alertBuilder
                .setTitle(title)
                .setMessage(message);
        alertBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
    }
}
