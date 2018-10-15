package dk.itcamp.taxicamp.standard;

import android.location.Location;

public class TaxiRunner implements Runnable {

    private Taxi taxi;

    public TaxiRunner(Taxi taxi) {
        this.taxi = taxi;
    }


    @Override
    public void run() {

        int i = 0;
        while (true) {
            Location latest = this.taxi.getLocation();

            latest.setLongitude(latest.getLongitude() - 0.01);
            latest.setLatitude(latest.getLatitude() - 0.01);
            this.taxi.setLocation(latest);

            /*if (LocationUtility.locationEquals(Singleton.getInstance().currentLocation, latest)) {
                break;
            }*/

            if (i == 10) {
                break;
            }

            i++;
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}