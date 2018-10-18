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

        listOfTaxis.add(newTaxi("Jens Ole Pedersen", -122.043, 37.411));
        listOfTaxis.add(newTaxi("Michael Hansen", -122.053, 37.421));
        listOfTaxis.add(newTaxi("Lone Jensen", -122.023, 37.430));
        listOfTaxis.add(newTaxi("Anni Britta Hansen", -122.013, 37.220));
        listOfTaxis.add(newTaxi("Kasper Hansen", -122.043, 37.420));
        listOfTaxis.add(newTaxi("Jonas Mikkelsen", -122.063, 37.320));
        listOfTaxis.add(newTaxi("Ulrik Henriksen", -122.073, 37.422));
        listOfTaxis.add(newTaxi("Earl Sweatshirt", -122.013, 37.422));
        listOfTaxis.add(newTaxi("Frank Ocean", -122.042, 37.422));
        listOfTaxis.add(newTaxi("Connor Youngblood", -122.043, 37.422));

        Singleton.getInstance().taxis.addAll(listOfTaxis);
    }

    private static Taxi newTaxi(String name, double longitude, double latitude) {
        Location location = new Location(name);
        location.setLongitude(longitude);
        location.setLatitude(latitude);
        return new Taxi(name, location, Singleton.getInstance().googleMap);
    }
}
