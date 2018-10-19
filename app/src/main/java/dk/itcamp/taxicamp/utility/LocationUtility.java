package dk.itcamp.taxicamp.utility;

import android.location.Location;

import com.google.android.gms.maps.model.LatLng;

import java.text.DecimalFormat;

public class LocationUtility {
    public static LatLng locationToLatLng(Location location) {
        if (location != null) {
            return new LatLng(location.getLatitude(), location.getLongitude());
        }

        return null;
    }

    public static boolean locationEquals(Location location, Location other) {
        DecimalFormat value = new DecimalFormat("#.#");
        return value.format(location.getLatitude()).equals(value.format(other.getLatitude()))
                && value.format(location.getLongitude()).equals(value.format(other.getLongitude()));
    }

}
