package arnold.eureka_mobile.Activity.Homepage;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

import arnold.eureka_mobile.Activity.SelectHawkerActivity;
import arnold.eureka_mobile.Entity.Canteen;
import arnold.eureka_mobile.R;
import arnold.eureka_mobile.TestCreator;

public class CanteenSelectorFragment extends android.support.v4.app.Fragment {

    private static final String TAG = "CanteenSelectorFrag";
    private static Gson gson;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gson = new GsonBuilder().create();
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

        ArrayList<Canteen> list = TestCreator.getTestCanteenList(); // TODO: Testing only
        RecyclerView.Adapter listAdapter = new RecyclerListAdapter(list);
        recyclerView.setAdapter(listAdapter);
    }

    public static class RecyclerListAdapter extends RecyclerView.Adapter<RecyclerListAdapter.ViewHolder> {
        private ArrayList<Canteen> list;

        // constructor
        public RecyclerListAdapter(ArrayList<Canteen> list) {
            this.list = list;
        }

        // Create new views (invoked by the layout manager)
        @Override
        public RecyclerListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemlist_canteen, parent, false); // create a new view
            ViewHolder vh = new ViewHolder(v); // set the view's size, margins, paddings and layout parameters
            return vh;
        }

        // Replace the contents of a view (invoked by the layout manager)
        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            // - get element from your dataset at this position
            // - replace the contents of the view with that element
//            holder.canteenName.setText(list.get(position).getName());
//            holder.canteenAddress.setText(list.get(position).getAddress());
        }

        @Override
        public int getItemCount() {
            return list.size();
        }
// Provide a reference to the views for each data item
        // Complex data items may need more than one view per item, and
        // you provide access to all the views for a data item in a view holder
        public class ViewHolder extends RecyclerView.ViewHolder {
//            private TextView canteenName;
//            private TextView canteenAddress;


            public ViewHolder(View v) {
                super(v);
//                canteenName = (TextView) v.findViewById(R.id.canteenName);
//                canteenAddress = (TextView) v.findViewById(R.id.canteenAddress);

                v.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Context context = v.getContext(); // get context

                        int position = getPosition();
                        Canteen selectedCanteen = list.get(position);
                        Log.d(TAG, "Here1: " + selectedCanteen);

                        Intent intent = new Intent(context, SelectHawkerActivity.class);
                        intent.putExtra("canteen", gson.toJson(selectedCanteen));
                        Log.i(TAG, "Starting SelectHawkerActivity");
                        context.startActivity(intent);
                    }
                });
            }
        }

    }




}
