package dk.itcamp.taxicamp.standard;

import android.location.Location;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class Taxi {
    private String driverName;
    private Location location;
    private Marker marker;
    private GoogleMap googleMap;

    public Taxi(String driverName, Location location, GoogleMap googleMap) {
        this.driverName = driverName;
        this.googleMap = googleMap;
        this.location = location;

        MarkerOptions markerOptions = new MarkerOptions()
                .position(new LatLng(this.location.getLatitude(), this.location.getLongitude()))
                .title(this.driverName);

        this.marker = this.googleMap.addMarker(markerOptions);
    }

    public String getDriverName() {
        return this.driverName;
    }

    public Location getLocation() {
        return this.location;
    }

    public Marker getTaxiMarker() {
        return this.marker;
    }

    public void setLocation(Location location) {
        this.location = location;
        this.marker.setPosition(new LatLng(this.location.getLatitude(), this.location.getLongitude()));
    }
}
