package arnold.eureka_mobile.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

import arnold.eureka_mobile.Activity.ShoppingCart.ShoppingCartActivity;
import arnold.eureka_mobile.Entity.Cart;
import arnold.eureka_mobile.Entity.Food;
import arnold.eureka_mobile.Entity.Stall;
import arnold.eureka_mobile.R;

public class SelectFoodActivity extends ActionBarActivity {

    private final static String TAG = "SelectFoodAct";
    private static Gson gson;
    private static SharedPreferences sharedPref;
    private static SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_food);

        gson = new GsonBuilder().create();
        sharedPref = getSharedPreferences(getString(R.string.app_key), MODE_PRIVATE);
        editor = sharedPref.edit();

        ActionBar actionBar = getSupportActionBar();
        Log.d(TAG, "Actionbar: " + actionBar);
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        loadRecyclerView();
        setFont();
    }

    public void setFont(){
        Typeface typeface = Typeface.createFromAsset(getAssets(), "RobotoCondensed-Regular.ttf");

        TextView title = (TextView) findViewById(R.id.title);
        title.setTypeface(typeface);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_homepage, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case android.R.id.home:
                finish();
                break;
            case R.id.action_shoppingCart:
                startActivity(new Intent(this, ShoppingCartActivity.class));
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }

    public void loadRecyclerView(){
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this); // use a linear layout manager
        recyclerView.setLayoutManager(layoutManager);

        Stall stall = gson.fromJson(getIntent().getStringExtra("stall"), Stall.class);
        ArrayList<Food> list = stall.getFoodList();
        RecyclerView.Adapter listAdapter = new FavouritesAdapter(list);
        recyclerView.setAdapter(listAdapter);
    }

    public static class FavouritesAdapter extends RecyclerView.Adapter<FavouritesAdapter.ViewHolder> {
        private ArrayList<Food> list;

        // constructor
        public FavouritesAdapter(ArrayList<Food> list) {
            this.list = list;
        }

        // Create new views (invoked by the layout manager)
        @Override
        public FavouritesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemlist_food, parent, false); // create a new view
            ViewHolder vh = new ViewHolder(v); // set the view's size, margins, paddings and layout parameters
            return vh;
        }

        // Replace the contents of a view (invoked by the layout manager)
        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            // - get element from your dataset at this position
            // - replace the contents of the view with that element
//            DecimalFormat NUMBER_FORMAT = new DecimalFormat("#.00");
            holder.foodName.setText(list.get(position).getName());
//            holder.foodPrice.setText("$" + NUMBER_FORMAT.format(list.get(position).getPrice()));
        }

        // Return the size of your dataset (invoked by the layout manager)
        @Override
        public int getItemCount() {
            return list.size();
        }

        // Provide a reference to the views for each data item
        // Complex data items may need more than one view per item, and
        // you provide access to all the views for a data item in a view holder
        public class ViewHolder extends RecyclerView.ViewHolder {
            TextView foodName;

            public ViewHolder(View v) {
                super(v);
                Typeface typeface = Typeface.createFromAsset(v.getContext().getAssets(), "RobotoCondensed-Regular.ttf");
                foodName = (TextView) v.findViewById(R.id.food_name);
                foodName.setTypeface(typeface);

                v.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.d(TAG, "Food selected");
                        Context context = v.getContext();
                        Food selectedFood = list.get(getPosition());

                        Cart cart = gson.fromJson(sharedPref.getString("cart", null), Cart.class);
                        if(cart == null) {
                            Log.d(TAG, "New cart created!");
                            cart = new Cart();
                        }
                        cart.addToCart(selectedFood);
                        editor.putString("cart", gson.toJson(cart)).commit();
                        Toast.makeText(context, "Item added to Cart!", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }

    }


}
