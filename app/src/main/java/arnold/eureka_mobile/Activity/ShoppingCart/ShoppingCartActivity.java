package arnold.eureka_mobile.Activity.ShoppingCart;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

import arnold.eureka_mobile.Activity.ShoppingCart.EmptyShoppingCartFragment;
import arnold.eureka_mobile.Entity.Cart;
import arnold.eureka_mobile.Entity.Food;
import arnold.eureka_mobile.R;
import arnold.eureka_mobile.TestCreator;

public class ShoppingCartActivity extends ActionBarActivity {

    private Gson gson;
    private SharedPreferences sharedPref;
    private SharedPreferences.Editor editor;
    private Cart cart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);

        gson = new GsonBuilder().create();
        sharedPref = getSharedPreferences(getString(R.string.app_key), MODE_PRIVATE);
        editor = sharedPref.edit();

        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true); // set actionBar to display back button
        }

        runFragmentTransition(); // load or refresh fragments
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)   {
        getMenuInflater().inflate(R.menu.menu_shopping_cart, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case android.R.id.home:
                finish();
                break;
            case R.id.action_delete:
                editor.remove("cart").commit();
                runFragmentTransition();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }

    private void runFragmentTransition(){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        cart = gson.fromJson(sharedPref.getString("cart", null), Cart.class);

        if(cart == null){
            fragmentTransaction.replace(R.id.fragment_container, new EmptyShoppingCartFragment());
        }else{
            fragmentTransaction.replace(R.id.fragment_container, new ShoppingCartListFragment());
        }
        fragmentTransaction.commit();
    }
}
