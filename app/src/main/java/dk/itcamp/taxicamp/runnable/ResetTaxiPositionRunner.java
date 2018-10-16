package dk.itcamp.taxicamp.runnable;

import android.location.Location;

import dk.itcamp.taxicamp.standard.Taxi;

public class ResetTaxiPositionRunner implements Runnable {
    private Taxi taxi;

    public ResetTaxiPositionRunner(Taxi taxi) {
        this.taxi = taxi;
    }

    @Override
    public void run() {
        Location location = new Location(this.taxi.getDriverName());

        double newLatitude = this.taxi.getLocation().getLatitude() + 0.009;
        double newLongitude = this.taxi.getLocation().getLongitude() - 0.009;

        location.setLatitude(newLatitude);
        location.setLongitude(newLongitude);
        this.taxi.setLocation(location);
    }
}
