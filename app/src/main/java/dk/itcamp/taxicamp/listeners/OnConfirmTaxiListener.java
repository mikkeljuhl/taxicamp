package dk.itcamp.taxicamp.listeners;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Handler;
import android.view.View;
import android.widget.Toast;


import java.math.*;

import dk.itcamp.taxicamp.runnable.TaxiHasArrived;
import dk.itcamp.taxicamp.standard.Singleton;
import dk.itcamp.taxicamp.standard.Taxi;
import dk.itcamp.taxicamp.standard.TaxiRunner;

public class OnConfirmTaxiListener implements DialogInterface.OnClickListener {
    private View view;
    private Taxi taxi;
    private Activity activity;

    private static final int SPEED_OF_TAXI = 10;

    public OnConfirmTaxiListener(View view, Taxi taxi, Activity activity) {
        this.view = view;
        this.taxi = taxi;
        this.activity = activity;
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        Toast.makeText(this.view.getContext(), "Din taxi er nu bestilt!", Toast.LENGTH_LONG).show();
        this.callTheTaxi();
    }

    private void callTheTaxi() {
        Handler handler = new Handler();

        // Extract latitude and longitude from currentLocation
        BigDecimal currentLat = BigDecimal.valueOf(Singleton.getInstance().currentLocation.getLatitude());
        BigDecimal currentLng = BigDecimal.valueOf(Singleton.getInstance().currentLocation.getLongitude());

        // Extract latitude and longitude from taxi location
        BigDecimal taxiLat = BigDecimal.valueOf(this.taxi.getLocation().getLatitude());
        BigDecimal taxiLng = BigDecimal.valueOf(this.taxi.getLocation().getLongitude());

        // Find the difference between the taxi location and the current loaction
        BigDecimal latDifference = currentLat.subtract(taxiLat);
        BigDecimal lngDifference = currentLng.subtract(taxiLng);

        if (latDifference.abs().compareTo(lngDifference.abs()) == 0) {
            int amountOfLoops = this.findAmountOfLoops(latDifference);
            this.initiateTaxiRunners(handler, amountOfLoops);
        } else {
            this.initiateTaxiRunners(handler, this.findAmountOfLoops(lngDifference));
        }
    }

    private void initiateTaxiRunners(Handler handler, int loops) {
        for(int i = 0; i < loops ; i++) {
            handler.postDelayed(new TaxiRunner(this.taxi), i * OnConfirmTaxiListener.SPEED_OF_TAXI);
        }

        handler.postDelayed(new TaxiHasArrived(this.view), (loops + 1) * OnConfirmTaxiListener.SPEED_OF_TAXI);
    }

    public int findAmountOfLoops(BigDecimal difference) {
        return difference.abs().multiply(BigDecimal.valueOf(1000)).setScale(0, BigDecimal.ROUND_CEILING).intValue();
    }


}
