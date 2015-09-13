package arnold.eureka_mobile.Activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

import arnold.eureka_mobile.Connection.NetworkSingleton;
import arnold.eureka_mobile.Controller.LoginController;
import arnold.eureka_mobile.R;

public class LoginActivity extends Activity {

    private static final String TAG = "eureka/LoginAct";
    private Gson gson;
    private SharedPreferences sharedPref;
    private SharedPreferences.Editor editor;
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

        setFont();

//        loadFacebookSDK();

    }

    public void setFont(){
        Typeface typeface = Typeface.createFromAsset(getAssets(), "JosefinSans-Regular.ttf");

        Button loginButton = (Button) findViewById(R.id.button_login);
        Button forgotButton = (Button) findViewById(R.id.button_forgot_password);
        EditText inputUsername = (EditText) findViewById(R.id.login_username);
        EditText inputPassword = (EditText) findViewById(R.id.login_password);
        loginButton.setTypeface(typeface);
        forgotButton.setTypeface(typeface);
        inputUsername.setTypeface(typeface);
        inputPassword.setTypeface(typeface);
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

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        callbackManager.onActivityResult(requestCode, resultCode, data);
//    }

    public void onLoginButtonClick(View view){
        TextView inputUsername = (TextView) findViewById(R.id.login_username);
        TextView inputPassword = (TextView) findViewById(R.id.login_password);
        String strUsername = inputUsername.getText().toString();
        String strPassword = inputPassword.getText().toString();

        // retrieves the LoginController which handles the processing logic
        LoginController loginController = new LoginController(this);
        loginController.processUserCredentialsTEST(strUsername, strPassword); // process logic TODO: TESTING only
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
