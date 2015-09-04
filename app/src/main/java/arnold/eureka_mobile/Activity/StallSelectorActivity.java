package arnold.eureka_mobile.Activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import java.util.HashSet;
import java.util.Set;

import arnold.eureka_mobile.Entity.Food;
import arnold.eureka_mobile.R;

public class StallSelectorActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recyclerview);

        loadRecyclerView();
    }

    public void loadRecyclerView(){
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        // specify an adapter (see also next example)
//        ArrayList<Task> taskList = TestCreator.createTestTasks2();
//        ArrayList<Task> myDataset = taskList;
        Set<Food> dataSet = new HashSet<>();
        dataSet.add(new Food());
        dataSet.add(new Food());
        dataSet.add(new Food());
        RecyclerView.Adapter taskListAdapter = new FavouritesAdapter(dataSet);
        recyclerView.setAdapter(taskListAdapter);
    }

    public static class FavouritesAdapter extends RecyclerView.Adapter<FavouritesAdapter.ViewHolder> {
        private Set<Food> dataSet;
        //        Gson gson = new GsonBuilder().setDateFormat(R.string.date_format).create();

        // constructor
        public FavouritesAdapter(Set<Food> dataSet) {
            this.dataSet = dataSet;
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
//                view = v;

//                recipient = (TextView)view.findViewById(R.id.content_receiver);
//                address = (TextView)view.findViewById(R.id.content_address);
//                time = (TextView)view.findViewById(R.id.content_time);
//                orderNo = (TextView)view.findViewById(R.id.content_orderNo);

                v.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Context context = v.getContext();
                        context.startActivity(new Intent(context, FoodMenuActivity.class));
                    }
                });
            }
        }



        // Create new views (invoked by the layout manager)
        @Override
        public FavouritesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_stall_selector, parent, false); // create a new view
            ViewHolder vh = new ViewHolder(v); // set the view's size, margins, paddings and layout parameters
            return vh;
        }

        // Replace the contents of a view (invoked by the layout manager)
        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            // - get element from your dataset at this position
            // - replace the contents of the view with that element

//            holder.recipient.setText(dataSet.get(position).getReceiverName());
//            holder.address.setText(dataSet.get(position).getEndAddress());
////            holder.time.setText(dataSet.get(position).getPlanEndTime().toString());
//            holder.orderNo.setText(String.valueOf(dataSet.get(position).getTaskId()));
        }

        // Return the size of your dataset (invoked by the layout manager)
        @Override
        public int getItemCount() {
            return dataSet.size();
        }

    }

}
