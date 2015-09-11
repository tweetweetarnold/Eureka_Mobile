package arnold.eureka_mobile.Activity.ShoppingCart;

import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.text.DecimalFormat;
import java.util.ArrayList;

import arnold.eureka_mobile.Entity.Cart;
import arnold.eureka_mobile.Entity.Food;
import arnold.eureka_mobile.Entity.Hawker;
import arnold.eureka_mobile.R;

public class ShoppingCartListFragment extends android.support.v4.app.Fragment {

    private static Gson gson;
    private static SharedPreferences sharedPref;
    private static SharedPreferences.Editor editor;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        gson = new GsonBuilder().create();
        sharedPref = getActivity().getSharedPreferences(getString(R.string.app_key), Context.MODE_PRIVATE);
        editor = sharedPref.edit();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_shopping_cart_filled, container, false);
        loadRecyclerView(view);
        return view;
    }

    public void loadRecyclerView(View view){
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity()); // use a linear layout manager
        recyclerView.setLayoutManager(layoutManager);

        Cart cart = gson.fromJson(sharedPref.getString("cart", null), Cart.class);
        ArrayList<Food> list = cart.getCart();

        RecyclerView.Adapter listAdapter = new RecyclerListAdapter(list);
        recyclerView.setAdapter(listAdapter);
    }

    public static class RecyclerListAdapter extends RecyclerView.Adapter<RecyclerListAdapter.ViewHolder> {
        private ArrayList<Food> list;

        // constructor
        public RecyclerListAdapter(ArrayList<Food> list) {
            this.list = list;
        }

        // Create new views (invoked by the layout manager)
        @Override
        public RecyclerListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemlist_shopping_cart, parent, false); // create a new view
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
                foodName = (TextView) v.findViewById(R.id.food_name);
                foodPrice = (TextView) v.findViewById(R.id.food_price);

                v.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    }
                });
            }
        }

    }


}
