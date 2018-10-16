package dk.itcamp.taxicamp.standard;

import android.location.Location;


public class TaxiUtility {

    public static Taxi findClosestTaxi() {
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

    public static void initializeTaxis() {
        Singleton.getInstance().taxis.add(newTaxi("Taxi Driver 1"));
    }

    private static Taxi newTaxi(String name) {
        Location location = new Location(name);
        location.setLongitude(-122.043);
        location.setLatitude(37.411);
        return new Taxi(name, location, Singleton.getInstance().googleMap);
    }
}
