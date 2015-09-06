package arnold.eureka_mobile.Activity.ShoppingCart;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

import arnold.eureka_mobile.Activity.ShoppingCart.EmptyShoppingCartFragment;
import arnold.eureka_mobile.R;

public class ShoppingCartActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);

        // set actionBar to display back button
        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }



//        System.out.println("here1");
//        try {
//            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
//            fragmentTransaction.replace(R.id.fragment, new EmptyShoppingCartFragment());
//            System.out.println("here2");
//            fragmentTransaction.addToBackStack(null);
//            fragmentTransaction.commit();
//            System.out.println("here3");
//        }catch(Exception e){
//            System.out.println("errormsg: " + e.getMessage());
//            e.printStackTrace();
//        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)   {
        getMenuInflater().inflate(R.menu.menu_homepage, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case android.R.id.home:
                finish();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }
}
