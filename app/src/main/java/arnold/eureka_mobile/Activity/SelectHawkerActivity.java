package arnold.eureka_mobile.Activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import arnold.eureka_mobile.Entity.Canteen;
import arnold.eureka_mobile.Entity.Food;
import arnold.eureka_mobile.Entity.Hawker;
import arnold.eureka_mobile.R;

public class SelectHawkerActivity extends ActionBarActivity {

    private static Gson gson;
    private final String TAG = "SelectHawkerActivity";
    private Canteen selectedCanteen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recyclerview);

        System.out.println("selectHawker1");
        gson = new GsonBuilder().create();
        selectedCanteen = gson.fromJson(getIntent().getStringExtra("canteen"), Canteen.class);
        loadRecyclerView();
    }

    public void loadRecyclerView(){
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);
        System.out.println("selectHawker2");
        // use a linear layout manager
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

//        selectedCanteen = gson.fromJson(getIntent().getStringExtra("canteen"), Canteen.class);
        Log.d(TAG, "Here2: " + selectedCanteen);
        ArrayList<Hawker> list = selectedCanteen.getHawkerList();
        RecyclerView.Adapter taskListAdapter = new FavouritesAdapter(list);
        recyclerView.setAdapter(taskListAdapter);
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
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemlist_stall, parent, false); // create a new view
            ViewHolder vh = new ViewHolder(v); // set the view's size, margins, paddings and layout parameters
            return vh;
        }

        // Replace the contents of a view (invoked by the layout manager)
        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            // - get element from your dataset at this position
            // - replace the contents of the view with that element
//            holder.recipient.setText(dataSet.get(position).getReceiverName());
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
//            public View view;
//            public TextView recipient;
//            public TextView address;
//            public TextView time;
//            public TextView orderNo;

            public ViewHolder(View v) {
                super(v);
//                recipient = (TextView)view.findViewById(R.id.content_receiver);
                v.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Context context = v.getContext();
                        context.startActivity(new Intent(context, SelectFoodActivity.class));
                    }
                });
            }
        }

    }

}
