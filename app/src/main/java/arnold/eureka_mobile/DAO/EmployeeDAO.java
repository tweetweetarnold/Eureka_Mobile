package arnold.eureka_mobile.DAO;

import android.content.Context;
import android.location.GpsStatus;
import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONException;
import org.json.JSONObject;

import arnold.eureka_mobile.Connection.NetworkSingleton;
import arnold.eureka_mobile.Entity.Employee;
import arnold.eureka_mobile.TestCreator;

/**
 * Created by Arnold on 9/1/2015.
 */
public class EmployeeDAO {

    private static final String TAG = "eureka/employeeDAO";
    private static NetworkSingleton network;
    private static Gson gson;
    private static Employee employee;

    public EmployeeDAO(Context context){
        network = NetworkSingleton.getInstance(context);
        gson = new GsonBuilder().create();
    }

    public static Employee getEmployee(String username){
        String url = "";
        JsonObjectRequest request = new JsonObjectRequest(url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                try {
                    String status = jsonObject.getString("status");
                    if (status.equals("ok")){
                        Employee e = gson.fromJson(jsonObject.getString("message"), Employee.class);
                        setEmployee(e);
                        Log.i(TAG, "Employee retrieved");
                    }
                } catch (JSONException e) {
                    Log.e(TAG, "getEmployee JSONException thrown");
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.e(TAG, "getEmployee Volley Error");
                volleyError.printStackTrace();
            }
        });
        network.addToRequestQueue(request);
//        return employee;
        return TestCreator.getTestUser();
    }

    public static void setEmployee(Employee e){
        employee = e;
    }
}
