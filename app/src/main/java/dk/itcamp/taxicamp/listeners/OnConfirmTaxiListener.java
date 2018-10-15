package dk.itcamp.taxicamp.listeners;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Handler;
import android.view.View;
import android.widget.Toast;


import dk.itcamp.taxicamp.standard.Taxi;
import dk.itcamp.taxicamp.standard.TaxiRunner;

public class OnConfirmTaxiListener implements DialogInterface.OnClickListener {
    private View view;
    private Taxi taxi;
    private Activity activity;

    public OnConfirmTaxiListener(Activity activity, View view, Taxi taxi) {
        this.view = view;
        this.activity = activity;
        this.taxi = taxi;
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        Toast.makeText(this.view.getContext(), "Din taxi er nu bestilt!", Toast.LENGTH_LONG).show();

        Handler handler = new Handler();
        handler.postDelayed(new TaxiRunner(this.taxi), 3000);
        handler.postDelayed(new TaxiRunner(this.taxi), 6000);
        handler.postDelayed(new TaxiRunner(this.taxi), 9000);

        // this.activity.runOnUiThread(new TaxiRunner(this.taxi));
    }
}
