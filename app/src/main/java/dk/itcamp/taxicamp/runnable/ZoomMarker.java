package dk.itcamp.taxicamp.runnable;

import android.location.Location;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import dk.itcamp.taxicamp.utility.LocationUtility;
import dk.itcamp.taxicamp.standard.Singleton;

public class ZoomMarker implements Runnable {

    private GoogleMap googleMap;

    public ZoomMarker(GoogleMap googleMap) {
        this.googleMap = googleMap;
    }

    @Override
    public void run() {
        Location currentLocation = Singleton.getInstance().currentLocation;
        LatLng currentLatLngLocation = LocationUtility.locationToLatLng(currentLocation);

        if (currentLatLngLocation != null) {
            this.googleMap.addMarker(new MarkerOptions().position(currentLatLngLocation).title("Current location"));
            googleMap.moveCamera(CameraUpdateFactory.newLatLng(LocationUtility.locationToLatLng(currentLocation)));
            CameraPosition cameraPosition = new CameraPosition.Builder().target(LocationUtility.locationToLatLng(currentLocation)).zoom(14).build();
            googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        }
    }
}
