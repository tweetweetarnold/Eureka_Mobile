package arnold.eureka_mobile.Activity;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import arnold.eureka_mobile.Entity.Food;
import arnold.eureka_mobile.Entity.Hawker;
import arnold.eureka_mobile.R;

public class SelectFoodActivity extends ActionBarActivity {

    private Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recyclerview);

        gson = new GsonBuilder().create();
        loadRecyclerView();
    }

    public void loadRecyclerView(){
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this); // use a linear layout manager
        recyclerView.setLayoutManager(layoutManager);

        Hawker hawker = gson.fromJson(getIntent().getStringExtra("hawker"), Hawker.class);
        ArrayList<Food> list = hawker.getFoodList();
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
            DecimalFormat NUMBER_FORMAT = new DecimalFormat("#.00");
            holder.foodName.setText(list.get(position).getName());
            holder.foodPrice.setText("$" + NUMBER_FORMAT.format(list.get(position).getPrice()));
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
            TextView foodPrice;

            public ViewHolder(View v) {
                super(v);
                foodName = (TextView) v.findViewById(R.id.foodName);
                foodPrice = (TextView) v.findViewById(R.id.foodPrice);

                v.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    }
                });
            }
        }

    }


}
