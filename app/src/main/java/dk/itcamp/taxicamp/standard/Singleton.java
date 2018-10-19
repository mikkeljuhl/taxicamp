package dk.itcamp.taxicamp.standard;

import android.location.Location;

import com.google.android.gms.maps.GoogleMap;

import java.util.ArrayList;

public class Singleton {
    private static Singleton instance = null;



    public ArrayList<Taxi> taxis = new ArrayList<>();
    public Location currentLocation;
    public GoogleMap googleMap = null;

    // static method to create instance of Singleton class
    public static Singleton getInstance() {
        if (instance == null) {

            instance = new Singleton();


        }
        Location location = new Location("Current Location");
        location.setLongitude(10.18764207);
        location.setLatitude(56.17258293);
        instance.currentLocation = location;

        return instance;
    }
}
