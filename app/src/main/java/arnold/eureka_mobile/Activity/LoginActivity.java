package arnold.eureka_mobile.Activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONObject;

import arnold.eureka_mobile.Controller.AccessController;
import arnold.eureka_mobile.Entity.User;
import arnold.eureka_mobile.R;
import arnold.eureka_mobile.TestCreator;

public class LoginActivity extends Activity {

    private static final String TAG = "arnold/LoginAct";
    private Gson gson;
    private SharedPreferences sharedPref;
    private SharedPreferences.Editor editor;
    private CallbackManager callbackManager;
    private AccessToken token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_login);

//        initialize
        gson = new GsonBuilder().create();
        sharedPref = this.getSharedPreferences(getString(R.string.app_key), MODE_PRIVATE);
        editor = sharedPref.edit();

//        loadFacebookSDK();

    }

//    public void loadFacebookSDK(){
//        FacebookSdk.sdkInitialize(getApplicationContext());
//        LoginManager loginManager = LoginManager.getInstance();
//        callbackManager = CallbackManager.Factory.create();
//        LoginButton loginButton = (LoginButton) findViewById(R.id.facebook_login_button);
////        loginButton.setReadPermissions("user_friends"); //not sure what this is for
//
//        loginManager.logInWithReadPermissions(this, Arrays.asList("public_profile", "user_friends"));
//
//        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
//            @Override
//            public void onSuccess(LoginResult loginResult) {
//                Log.i(TAG, "Facebook login success!");
//                AccessToken token2 = loginResult.getAccessToken();
//                System.out.println("MyToken: " + token2);
//                setToken(token2);
//                editor.putString("token", token2.toString());
//                editor.apply();
//                startActivity(new Intent(getApplicationContext(), HomepageActivity.class));
////                getUserProfile();
//            }
//
//            @Override
//            public void onCancel() {
//                Log.i(TAG, "Facebook login cancelled!");
//            }
//
//            @Override
//            public void onError(FacebookException e) {
//                Log.i(TAG, "Facebook login error!");
//            }
//        });
//
//    }

    public void setToken(AccessToken token){
        this.token = token;
    }

    public void getUserProfile(){
        new GraphRequest(token, "/me", null, HttpMethod.GET, new GraphRequest.Callback() {
            @Override
            public void onCompleted(GraphResponse graphResponse) {
                JSONObject object = graphResponse.getJSONObject();
                TextView text = (TextView) findViewById(R.id.login_appName);
                text.setText(object.toString());
            }
        }).executeAsync();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onResume() {
        super.onResume();
        TextView inputUsername = (TextView) findViewById(R.id.login_username);
        TextView inputPassword = (TextView) findViewById(R.id.login_password);
        inputUsername.setText("abc");
        inputPassword.setText("123");
    }

    public void onLoginButtonClick(View view){
        TextView inputUsername = (TextView) findViewById(R.id.login_username);
        TextView inputPassword = (TextView) findViewById(R.id.login_password);
        String strUsername = inputUsername.getText().toString();
        String strPassword = inputPassword.getText().toString();

        boolean result = AccessController.getInstance(getApplicationContext()).processLogin(strUsername, strPassword);

        User testUser = TestCreator.getTestUser();

        if (result) {
            Log.i(TAG, "User credentials valid.");
            editor.putString("user", gson.toJson(testUser));
            editor.apply();
            startActivity(new Intent(this, HomepageActivity.class));
        }else{
            Log.e(TAG, "Invalid credentials");
            runAlertDialog("Invalid credentials",
                    "Oops! Something went wrong!\nMake sure your username and password is correct.");
        }

    }

    public void runAlertDialog(String title, String message){
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(getApplicationContext());
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
