package arnold.eureka_mobile.Fragment;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import arnold.eureka_mobile.R;

public class HomepageFragment extends android.support.v4.app.Fragment {

    private SharedPreferences sharedPref;
    private Gson gson;



    public HomepageFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        initialize
        sharedPref = this.getActivity().getSharedPreferences(getString(R.string.app_key), Context.MODE_PRIVATE);
        gson = new GsonBuilder().create();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_homepage, container, false);

        String token = sharedPref.getString("token", null);
        if(token != null){
            TextView id = (TextView) view.findViewById(R.id.content_userID);
            id.setText(token);
        }
        return view;
    }


}
