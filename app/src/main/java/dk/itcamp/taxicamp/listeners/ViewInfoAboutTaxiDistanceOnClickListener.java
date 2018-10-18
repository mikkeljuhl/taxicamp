package dk.itcamp.taxicamp.listeners;

import android.app.Activity;
import android.location.Location;
import android.support.design.widget.Snackbar;
import android.view.View;

import java.math.BigDecimal;

import dk.itcamp.taxicamp.standard.Singleton;
import dk.itcamp.taxicamp.utility.TaxiUtility;

public class ViewInfoAboutTaxiDistanceOnClickListener implements View.OnClickListener {
    private Activity activity;

    public ViewInfoAboutTaxiDistanceOnClickListener(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void onClick(View v) {
        Snackbar.make(v, this.textForClosestTaxi(), Snackbar.LENGTH_LONG)
                .setAction(this.orderTaxiText(), new OrderTaxiOnClickListener(activity, TaxiUtility.findClosestTaxi(), this.distance())).show();
    }

    private String orderTaxiText() {
        return "Bestil Taxi";
    }

    private BigDecimal distance() {
        Location currentLocation = Singleton.getInstance().currentLocation;
        BigDecimal distanceToTaxi  = BigDecimal.valueOf(TaxiUtility.findClosestTaxi().getLocation().distanceTo(currentLocation) / 1000);

        return distanceToTaxi.setScale(2, BigDecimal.ROUND_CEILING);
    }

    private String textForClosestTaxi() {
        Location currentLocation = Singleton.getInstance().currentLocation;
        BigDecimal distanceToTaxi  = BigDecimal.valueOf(TaxiUtility.findClosestTaxi().getLocation().distanceTo(currentLocation) / 1000);

        // Average speed is 40 km/h
        BigDecimal distanceInMinutes = distanceToTaxi.divide(BigDecimal.valueOf(50)).multiply(BigDecimal.valueOf(60));

        return "Den taxi der er tættest på er " + distanceInMinutes.setScale(2, BigDecimal.ROUND_CEILING) + " minutter væk";
    }
}
