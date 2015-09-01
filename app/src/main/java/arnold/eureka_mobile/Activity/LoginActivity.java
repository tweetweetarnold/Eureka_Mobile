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

import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.RequestFuture;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONObject;

import java.util.concurrent.TimeUnit;

import arnold.eureka_mobile.Connection.NetworkSingleton;
import arnold.eureka_mobile.Controller.LoginController;
import arnold.eureka_mobile.Entity.Employee;
import arnold.eureka_mobile.R;
import arnold.eureka_mobile.TestCreator;

public class LoginActivity extends Activity {

    private static final String TAG = "eureka/LoginAct";
    private Gson gson;
    private SharedPreferences sharedPref;
    private SharedPreferences.Editor editor;
    private CallbackManager callbackManager;
    private NetworkSingleton network;
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
        network = NetworkSingleton.getInstance(this);

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
                TextView text = (TextView) findViewById(R.id.appName);
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

        try {
            LoginController loginController = new LoginController(this);
            boolean authenticate = loginController.authenticateUser(strUsername, strPassword);

            if (authenticate) {
                Log.i(TAG, "User token retrieved.");
                Employee testEmployee = TestCreator.getTestUser();
                editor.putString("user", gson.toJson(testEmployee)).commit();
                startActivity(new Intent(this, Homepage2Activity.class));
            } else {
                Log.e(TAG, "Invalid credentials");
                runAlertDialog("Invalid credentials",
                        "Oops! Something went wrong!\nMake sure your username and password is correct.");
            }
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    public void goToSignUp(View view){
        startActivity(new Intent(getApplicationContext(), SignUpActivity.class));
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
