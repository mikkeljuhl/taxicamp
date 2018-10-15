package dk.itcamp.taxicamp.standard;

import android.location.Location;

public class TaxiRunner implements Runnable {

    private Taxi taxi;

    public TaxiRunner(Taxi taxi) {
        this.taxi = taxi;
    }


    @Override
    public void run() {
        Location latest = this.taxi.getLocation();
        latest.setLongitude(latest.getLongitude() - 0.01);
        latest.setLatitude(latest.getLatitude() - 0.01);
        this.taxi.setLocation(latest);
    }
}