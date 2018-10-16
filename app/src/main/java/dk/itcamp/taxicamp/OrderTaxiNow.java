package dk.itcamp.taxicamp;

import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import dk.itcamp.taxicamp.listeners.ViewInfoAboutTaxiDistanceOnClickListener;
import dk.itcamp.taxicamp.standard.LocationTracker;
import dk.itcamp.taxicamp.utility.LocationUtility;
import dk.itcamp.taxicamp.standard.Singleton;
import dk.itcamp.taxicamp.utility.TaxiUtility;

public class OrderTaxiNow extends AppCompatActivity implements OnMapReadyCallback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_taxi_now);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        }

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapView);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        Singleton.getInstance().googleMap = googleMap;
        this.ensurePermissionsAreOK();
        TaxiUtility.initializeTaxis();
        this.initializeAndReturnCurrentLocation();
        this.addCurrentLocationToMap();
        this.zoomAccordingToLocation();
        this.addOnClickListenerForFloatingActionButton();
    }

    private Location initializeAndReturnCurrentLocation() {
        if (Singleton.getInstance().currentLocation == null) {
            LocationTracker locationTracker = new LocationTracker(getApplicationContext(), Singleton.getInstance().googleMap);
            Singleton.getInstance().currentLocation = locationTracker.getCurrentLocation();
        }
        return Singleton.getInstance().currentLocation;
    }

    private void addCurrentLocationToMap() {
        LatLng currentLocation = new LatLng(Singleton.getInstance().currentLocation.getLatitude(), Singleton.getInstance().currentLocation.getLongitude());
        Singleton.getInstance().googleMap.addMarker(new MarkerOptions().position(currentLocation).title("Current location"));
    }

    private void ensurePermissionsAreOK() {
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            Singleton.getInstance().googleMap.setMyLocationEnabled(true);
        } else {
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        }
    }

    private void addOnClickListenerForFloatingActionButton() {
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new ViewInfoAboutTaxiDistanceOnClickListener(OrderTaxiNow.this));
    }

    private void zoomAccordingToLocation() {
        this.runOnUiThread(new Runnable(){
            public void run() {
                GoogleMap googleMap = Singleton.getInstance().googleMap;
                Location currentLocation = Singleton.getInstance().currentLocation;

                googleMap.moveCamera(CameraUpdateFactory.newLatLng(LocationUtility.locationToLatLng(currentLocation)));
                CameraPosition cameraPosition = new CameraPosition.Builder().target(LocationUtility.locationToLatLng(currentLocation)).zoom(14).build();
                googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
            }
        });

    }
}


