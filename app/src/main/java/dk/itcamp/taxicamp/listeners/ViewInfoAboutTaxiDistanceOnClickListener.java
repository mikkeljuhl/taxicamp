package dk.itcamp.taxicamp.listeners;

import android.app.Activity;
import android.location.Location;
import android.support.design.widget.Snackbar;
import android.view.View;

import java.text.DecimalFormat;

import dk.itcamp.taxicamp.standard.Taxi;

public class ViewInfoAboutTaxiDistanceOnClickListener implements View.OnClickListener {

    private String distanceToTaxi;
    private Taxi taxi;
    private Activity activity;


    public ViewInfoAboutTaxiDistanceOnClickListener(Activity activity, Taxi taxi, Location currentLocation) {
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);
        this.distanceToTaxi = df.format(taxi.getLocation().distanceTo(currentLocation) / 1000);
        this.taxi = taxi;
        this.activity = activity;
    }

    @Override
    public void onClick(View v) {
        Snackbar.make(v, "Den taxi der er tættest på er " + this.distanceToTaxi + " km væk", Snackbar.LENGTH_LONG)
                .setAction("Bestil Taxi", new OrderTaxiOnClickListener(activity, taxi)).show();
    }
}
