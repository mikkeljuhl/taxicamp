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

        System.out.println(closestTaxi.getLocation().getLongitude());
        return closestTaxi;
    }

    public static void initializeTaxis() {
        ArrayList<Taxi> listOfTaxis = new ArrayList<>();

        listOfTaxis.add(newTaxi("Earl Sweatshirt", -122.123, 37.411));
        listOfTaxis.add(newTaxi("Skepta", -122.14, 37.410));
        listOfTaxis.add(newTaxi("Frank Ocean", -122.100, 37.421));

        Singleton.getInstance().taxis.addAll(listOfTaxis);
    }

    private static Taxi newTaxi(String name, double longitude, double latitude) {
        Location location = new Location(name);
        location.setLongitude(longitude);
        location.setLatitude(latitude);
        return new Taxi(name, location, Singleton.getInstance().googleMap);
    }
}
