package arnold.eureka_mobile.Activity.Homepage;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.HashSet;
import java.util.Set;

import arnold.eureka_mobile.Entity.Food;
import arnold.eureka_mobile.R;

public class FavouritesFragment extends android.support.v4.app.Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.recyclerview, container, false);
        loadRecyclerView(view);
        return view;
    }

    public void loadRecyclerView(View view){
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        // specify an adapter (see also next example)
//        ArrayList<Task> taskList = TestCreator.createTestTasks2();
//        ArrayList<Task> myDataset = taskList;
        Set<Food> dataSet = new HashSet<>();
        dataSet.add(new Food());
        dataSet.add(new Food());
        dataSet.add(new Food());
        RecyclerView.Adapter taskListAdapter = new RecyclerListAdapter(dataSet);
        recyclerView.setAdapter(taskListAdapter);
    }

    public static class RecyclerListAdapter extends RecyclerView.Adapter<RecyclerListAdapter.ViewHolder> {
        private Set<Food> dataSet;
        //        Gson gson = new GsonBuilder().setDateFormat(R.string.date_format).create();

        // constructor
        public RecyclerListAdapter(Set<Food> dataSet) {
            this.dataSet = dataSet;
        }

        // Create new views (invoked by the layout manager)
        @Override
        public RecyclerListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemlist_favourites, parent, false); // create a new view
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
//                        Context context = v.getContext();
//                        int position = getPosition();
//                        Task selectedTask = dataSet.get(position);
//                        Log.d(TAG, "Position selected: " + position);
//
//                        Intent intent = new Intent(context, TaskActivity.class);
//                        intent.putExtra("task", gson.toJson(selectedTask));
//                        context.startActivity(intent);
                    }
                });
            }
        }

    }

}
