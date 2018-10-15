package dk.itcamp.taxicamp.listeners;

import android.content.DialogInterface;
import android.location.Location;
import android.view.View;
import android.widget.Toast;

import dk.itcamp.taxicamp.standard.Singleton;
import dk.itcamp.taxicamp.standard.Taxi;

public class OnConfirmTaxiListener implements DialogInterface.OnClickListener {
    private View view;
    private Taxi taxi;

    public OnConfirmTaxiListener(View view, Taxi taxi) {
        this.view = view;
        this.taxi = taxi;
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        Toast.makeText(this.view.getContext(), "Din taxi er nu bestilt!", Toast.LENGTH_LONG).show();

        Location location = new Location("New Location");
        location.setLongitude(0);
        location.setLatitude(0);

        this.taxi.setLocation(location);
    }
}
