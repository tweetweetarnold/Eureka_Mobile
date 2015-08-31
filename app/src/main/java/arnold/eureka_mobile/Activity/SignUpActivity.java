package arnold.eureka_mobile.Activity;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import arnold.eureka_mobile.R;

public class SignUpActivity extends Activity {

    private static final String TAG = "eureka/SignUpAct";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
    }

    public void onSignUpButtonClick(View view){

    }

    public void goToLogin(View view){
        finish();
    }

}
