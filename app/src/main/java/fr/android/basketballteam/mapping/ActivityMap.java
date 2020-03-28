package fr.android.basketballteam.mapping;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import fr.android.basketballteam.R;

public class ActivityMap extends AppCompatActivity  implements OnMapReadyCallback {

    private double latitude;
    private double longitude;

    private MapView map;
    private GoogleMap gmap;
    private TextView addressText;


    private static final String MAP_VIEW_BUNDLE_KEY = "AIzaSyCIRrhRWIMekP19XpUQwqQffwymfqmjjDY";

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        if(savedInstanceState != null){
            this.latitude = savedInstanceState.getDouble("Latitude");
            this.longitude = savedInstanceState.getDouble("Longitude");
        }else{
            Intent intent = getIntent();
            this.latitude = intent.getDoubleExtra("Latitude", 0.0);
            this.longitude = intent.getDoubleExtra("Longitude", 0.0);
        }

        map = findViewById(R.id.mapFrame);
        addressText = findViewById(R.id.address);

        Double[] array = new Double[2];
        array[0] = latitude;
        array[1] = longitude;

        Bundle mapViewBundle = null;
        if (savedInstanceState != null) {
            mapViewBundle = savedInstanceState.getBundle(MAP_VIEW_BUNDLE_KEY);
        }
        map.onCreate(mapViewBundle);
        map.getMapAsync(this);

        new AsyncLocationFinder(addressText, getApplicationContext()).execute(array);
    }

    @Override
    public void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        outState.putDouble("Latitude", latitude);
        outState.putDouble("Longitude", longitude);
        Bundle mapViewBundle = outState.getBundle(MAP_VIEW_BUNDLE_KEY);
        if (mapViewBundle == null) {
            mapViewBundle = new Bundle();
            outState.putBundle(MAP_VIEW_BUNDLE_KEY, mapViewBundle);
        }

        map.onSaveInstanceState(mapViewBundle);
    }


    @Override
    protected void onResume() {
        super.onResume();
        map.onResume();
    }

    @Override
    protected void onStart() {
        super.onStart();
        map.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        map.onStop();
    }
    @Override
    protected void onPause() {
        super.onPause();
        map.onPause();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        map.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        map.onLowMemory();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        gmap = googleMap;
        gmap.setMinZoomPreference(12);
        LatLng ny = new LatLng(latitude, longitude);
        gmap.moveCamera(CameraUpdateFactory.newLatLng(ny));
        gmap.addMarker(new MarkerOptions()
                .position(new LatLng(latitude, longitude))
                .title(""));
    }
}
