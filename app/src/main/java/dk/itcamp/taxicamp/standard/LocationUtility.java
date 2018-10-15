package dk.itcamp.taxicamp.standard;

import android.location.Location;

import com.google.android.gms.maps.model.LatLng;

public class LocationUtility {
    public static LatLng locationToLatLng(Location location) {
        return new LatLng(location.getLatitude(), location.getLongitude());
    }
}
