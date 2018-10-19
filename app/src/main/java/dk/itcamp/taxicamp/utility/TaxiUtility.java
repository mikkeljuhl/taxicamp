package dk.itcamp.taxicamp.utility;

import android.location.Location;

import java.util.ArrayList;

import dk.itcamp.taxicamp.standard.Singleton;
import dk.itcamp.taxicamp.standard.Taxi;


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
        ArrayList<Taxi> listOfTaxis = new ArrayList<>();

        listOfTaxis.add(newTaxi("Earl Sweatshirt", 10.180, 56.170));
        listOfTaxis.add(newTaxi("Skepta", 10.190, 56.180));
        listOfTaxis.add(newTaxi("Frank Ocean", 10.200, 56.150));

        Singleton.getInstance().taxis.addAll(listOfTaxis);
    }

    private static Taxi newTaxi(String name, double longitude, double latitude) {
        Location location = new Location(name);
        location.setLongitude(longitude);
        location.setLatitude(latitude);
        return new Taxi(name, location, Singleton.getInstance().googleMap);
    }
}
