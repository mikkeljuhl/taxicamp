package dk.itcamp.taxicamp.runnable;

import android.location.Location;

import java.util.Random;

import dk.itcamp.taxicamp.standard.Taxi;

public class ResetTaxiPositionRunner implements Runnable {
    private Taxi taxi;

    public ResetTaxiPositionRunner(Taxi taxi) {
        this.taxi = taxi;
    }

    @Override
    public void run() {
        Location location = new Location(this.taxi.getDriverName());

        double rangeMin = -0.050;
        double rangeMax = 0.050;
        Random r = new Random();
        double randomValueLat = rangeMin + (rangeMax - rangeMin) * r.nextDouble();
        double randomValueLng = rangeMin + (rangeMax - rangeMin) * r.nextDouble();

        double newLatitude = this.taxi.getLocation().getLatitude() + randomValueLat;
        double newLongitude = this.taxi.getLocation().getLongitude() + randomValueLng;

        location.setLatitude(newLatitude);
        location.setLongitude(newLongitude);
        this.taxi.setLocation(location);
    }
}
