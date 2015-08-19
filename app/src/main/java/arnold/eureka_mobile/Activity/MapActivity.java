package arnold.eureka_mobile.Activity;

import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import arnold.eureka_mobile.R;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback {

    private static final String TAG = "arnold/MapAct";
    private MapFragment mMapFragment;
    private static final float ZOOM_COUNTRY = 10;
    private static final float ZOOM_LOCATION = 16;
    private static final LatLng LOC_SINGAPORE = new LatLng(1.364499, 103.824353);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        GoogleMapOptions options = new GoogleMapOptions();
        options.mapType(GoogleMap.MAP_TYPE_NORMAL);
        mMapFragment = MapFragment.newInstance();

        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.map, mMapFragment);
        fragmentTransaction.commit();

        mMapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap map) {
        LatLng location = LOC_SINGAPORE;
        map.animateCamera(CameraUpdateFactory.newLatLngZoom(location, ZOOM_COUNTRY));
        map.addMarker(new MarkerOptions()
                .position(location)
                .title("New Location"));

        setUiSettings(map);
    }

    public void setUiSettings(GoogleMap map){
        UiSettings ui = map.getUiSettings();
        ui.setZoomControlsEnabled(true);
        ui.setCompassEnabled(true);
    }
}
