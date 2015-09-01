package arnold.eureka_mobile.Fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.HashSet;
import java.util.Set;

import arnold.eureka_mobile.Entity.Food;
import arnold.eureka_mobile.R;

public class FoodListFragment extends Fragment {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    public FoodListFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_food_list2, container, false);
        loadRecyclerView(view);
        return view;
    }

    public void loadRecyclerView(View view){
        RecyclerView recyclerView = (RecyclerView)view.findViewById(R.id.recyclerView);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        // specify an adapter (see also next example)
//        ArrayList<Task> taskList = TestCreator.createTestTasks1();
//        ArrayList<Task> myDataset = taskList;
        Set<Food> list = new HashSet<>();
        list.add(new Food());
        list.add(new Food());
        list.add(new Food());
        RecyclerView.Adapter listAdapter = new ListAdapter(list);
        recyclerView.setAdapter(listAdapter);
    }

    public static class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {
        private Set<Food> dataSet;

        // Provide a reference to the views for each data item
        // Complex data items may need more than one view per item, and
        // you provide access to all the views for a data item in a view holder
        public class ViewHolder extends RecyclerView.ViewHolder {
            public View view;
            public TextView recipient;
            public TextView address;
            public TextView time;
            public TextView orderNo;

            public ViewHolder(View v) {
                super(v);
                view = v;

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

        //        constructor
        public ListAdapter(Set<Food> myDataset) {
            dataSet = myDataset;
        }

        // Create new views (invoked by the layout manager)
        @Override
        public ListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.foodlist, parent, false); // create a new view
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
