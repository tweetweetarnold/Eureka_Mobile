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

import com.facebook.FacebookSdk;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import arnold.eureka_mobile.Entity.User;
import arnold.eureka_mobile.R;
import arnold.eureka_mobile.TestCreator;

public class LoginActivity extends Activity {

    private static final String TAG = "arnold/LoginAct";
    private Gson gson;
    private SharedPreferences sharedPref;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_login);

//        initialize
        gson = new GsonBuilder().create();
        sharedPref = this.getSharedPreferences(getString(R.string.app_key), MODE_PRIVATE);
        editor = sharedPref.edit();
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

//        for testing
        User testUser = TestCreator.getTestUser();

//        check if user is valid
        boolean authenticate = (strUsername.equals(testUser.getUsername()) && strPassword.equals(testUser.getPassword()));

        if(authenticate){
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


//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_login, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }
}
