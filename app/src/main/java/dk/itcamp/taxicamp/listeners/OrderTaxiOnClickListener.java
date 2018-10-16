package dk.itcamp.taxicamp.listeners;

import android.app.Activity;
import android.app.AlertDialog;
import android.view.View;

import dk.itcamp.taxicamp.standard.Taxi;

public class OrderTaxiOnClickListener implements View.OnClickListener  {

    private Taxi taxi;
    private Activity activity;

    public OrderTaxiOnClickListener(Activity activity, Taxi taxi) {
        this.taxi = taxi;
        this.activity = activity;
    }

    @Override
    public void onClick(View v) {
        new AlertDialog.Builder(v.getContext())
                .setTitle("Title")
                .setMessage("Do you really want to whatever?")
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setPositiveButton(android.R.string.yes, new OnConfirmTaxiListener(v, this.taxi, this.activity))
                .setNegativeButton(android.R.string.no, null).show();
    }
}
