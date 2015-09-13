package arnold.eureka_mobile.Activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

import arnold.eureka_mobile.Activity.ShoppingCart.ShoppingCartActivity;
import arnold.eureka_mobile.Entity.Canteen;
import arnold.eureka_mobile.Entity.Hawker;
import arnold.eureka_mobile.R;

public class SelectHawkerActivity extends ActionBarActivity {

    private static Gson gson;
    private final String TAG = "SelectHawkerAct";
    private Canteen selectedCanteen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recyclerview);

        gson = new GsonBuilder().create();
        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        loadRecyclerView();
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
        // use a linear layout manager
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        selectedCanteen = gson.fromJson(getIntent().getStringExtra("canteen"), Canteen.class);
        ArrayList<Hawker> list = selectedCanteen.getHawkerList();
        RecyclerView.Adapter listAdapter = new FavouritesAdapter(list);
        recyclerView.setAdapter(listAdapter);
    }

    public static class FavouritesAdapter extends RecyclerView.Adapter<FavouritesAdapter.ViewHolder> {
        private ArrayList<Hawker> list;

        // constructor
        public FavouritesAdapter(ArrayList<Hawker> list) {
            this.list = list;
        }

        // Create new views (invoked by the layout manager)
        @Override
        public FavouritesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemlist_hawker, parent, false); // create a new view
            ViewHolder vh = new ViewHolder(v); // set the view's size, margins, paddings and layout parameters
            return vh;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            // - get element from your dataset at this position
            // - replace the contents of the view with that element
//            holder.hawkerName.setText(list.get(position).getName());
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

            public ViewHolder(View v) {
                super(v);
                Typeface typeface = Typeface.createFromAsset(v.getContext().getAssets(), "RobotoCondensed-Regular.ttf");

                TextView hawkerName = (TextView) v.findViewById(R.id.hawker_name);
                hawkerName.setTypeface(typeface);

                v.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Context context = v.getContext();

                        int position = getPosition();
                        Hawker selectedHawker = list.get(position);

                        Intent intent = new Intent(context, SelectFoodActivity.class);
                        intent.putExtra("hawker", gson.toJson(selectedHawker));
                        context.startActivity(intent);
                    }
                });
            }
        }

    }

}