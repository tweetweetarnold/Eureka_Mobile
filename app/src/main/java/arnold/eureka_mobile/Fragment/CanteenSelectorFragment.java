package arnold.eureka_mobile.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.HashSet;
import java.util.Set;

import arnold.eureka_mobile.Activity.SelectStallActivity;
import arnold.eureka_mobile.Entity.Hawker;
import arnold.eureka_mobile.R;

public class CanteenSelectorFragment extends android.support.v4.app.Fragment {

    private static final String TAG = "CanteenSelectorFrag";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
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
        Set<Hawker> dataSet = new HashSet<>();
        dataSet.add(new Hawker());
        dataSet.add(new Hawker());
        dataSet.add(new Hawker());
        RecyclerView.Adapter taskListAdapter = new CanteenSelectorAdapter(dataSet);
        recyclerView.setAdapter(taskListAdapter);
    }

    public static class CanteenSelectorAdapter extends RecyclerView.Adapter<CanteenSelectorAdapter.ViewHolder> {
        private Set<Hawker> dataSet;

        // constructor
        public CanteenSelectorAdapter(Set<Hawker> dataSet) {
            this.dataSet = dataSet;
        }

        // Provide a reference to the views for each data item
        // Complex data items may need more than one view per item, and
        // you provide access to all the views for a data item in a view holder
        public class ViewHolder extends RecyclerView.ViewHolder {

            public ViewHolder(View v) {
                super(v);
//                view = v;
//                recipient = (TextView)view.findViewById(R.id.content_receiver);

                v.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Context context = v.getContext(); // get context

//                        int position = getPosition();
//                        Task selectedTask = dataSet.get(position);

                        Log.i(TAG, "Starting SelectStallActivity");
                        context.startActivity(new Intent(context, SelectStallActivity.class));
                    }
                });
            }
        }



        // Create new views (invoked by the layout manager)
        @Override
        public CanteenSelectorAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_canteen, parent, false); // create a new view
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

        @Override
        public int getItemCount() {
            return dataSet.size();
        }

    }




}
