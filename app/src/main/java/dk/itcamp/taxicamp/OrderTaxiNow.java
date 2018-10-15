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
import dk.itcamp.taxicamp.standard.Singleton;
import dk.itcamp.taxicamp.standard.Taxi;

public class OrderTaxiNow extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap googleMap;
    private Taxi closestTaxi = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_taxi_now);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapView);
        mapFragment.getMapAsync(this);
    }

    private void initializeTaxis(GoogleMap googleMap) {
        Singleton.getInstance().taxis.add(newTaxi("Taxi Driver 1", googleMap));
    }

    private Taxi newTaxi(String name, GoogleMap googleMap) {
        Location location = new Location(name);
        location.setLongitude(-122.083);
        location.setLatitude(37.421);
        return new Taxi(name, location, googleMap);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;

        this.initializeTaxis(this.googleMap);

        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            googleMap.setMyLocationEnabled(true);
        } else {
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        }

        LocationTracker locationTracker = new LocationTracker(getApplicationContext(), this.googleMap);
        Location location = locationTracker.getCurrentLocation();
        Singleton.getInstance().currentLocation = location;

        LatLng currentLocation = new LatLng(location.getLatitude(), location.getLongitude());
        this.googleMap.addMarker(new MarkerOptions().position(currentLocation).title("Current location"));

        this.zoomAccordingToLocation(currentLocation);
        this.closestTaxi = this.findClosestTaxi();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new ViewInfoAboutTaxiDistanceOnClickListener(this.closestTaxi, Singleton.getInstance().currentLocation));
    }

    private void zoomAccordingToLocation(LatLng location) {
        this.googleMap.moveCamera(CameraUpdateFactory.newLatLng(location));
        CameraPosition cameraPosition = new CameraPosition.Builder().target(location).zoom(14).build();
        this.googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
    }

    private Taxi findClosestTaxi() {
        Taxi closestTaxi = null;
        Location currentLocation = Singleton.getInstance().currentLocation;

        for (Taxi taxi : Singleton.getInstance().taxis) {
            if (closestTaxi == null) { closestTaxi = taxi; }

            if (currentLocation.distanceTo(taxi.getLocation()) < currentLocation.distanceTo(closestTaxi.getLocation())) {
                closestTaxi = taxi;
            }
        }

        return closestTaxi;
    }
}


