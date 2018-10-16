package dk.itcamp.taxicamp.runnable;

import android.location.Location;

import java.math.BigDecimal;
import java.text.DecimalFormat;

import dk.itcamp.taxicamp.standard.Singleton;
import dk.itcamp.taxicamp.standard.Taxi;

public class TaxiRunner implements Runnable {

    private Taxi taxi;

    public TaxiRunner(Taxi taxi) {
        this.taxi = taxi;
    }


    public boolean locationEquality(double lat, double otherLat) {
        BigDecimal lng = BigDecimal.valueOf(lat).setScale(3, BigDecimal.ROUND_CEILING);
        BigDecimal otherLng = BigDecimal.valueOf(otherLat).setScale(3, BigDecimal.ROUND_CEILING);

        return lng.subtract(otherLng).equals(BigDecimal.ZERO.setScale(3, BigDecimal.ROUND_CEILING));
    }

    @Override
    public void run() {
        Location latest = this.taxi.getLocation();
        Location currentLocation = Singleton.getInstance().currentLocation;

        if (!this.locationEquality(latest.getLatitude(), currentLocation.getLatitude())) {
            if (Math.abs(latest.getLatitude()) - Math.abs(currentLocation.getLatitude()) > 0) {
                latest.setLatitude(latest.getLatitude() - 0.001);
            } else {
                latest.setLatitude(latest.getLatitude() + 0.001);
            }
        }

        if (!this.locationEquality(latest.getLongitude(), currentLocation.getLongitude())) {
            if (Math.abs(latest.getLongitude()) - Math.abs(currentLocation.getLongitude()) > 0) {
                latest.setLongitude(latest.getLongitude() + 0.001);
            } else {
                latest.setLongitude(latest.getLongitude() - 0.001);
            }
        }

        this.taxi.setLocation(latest);
    }
}