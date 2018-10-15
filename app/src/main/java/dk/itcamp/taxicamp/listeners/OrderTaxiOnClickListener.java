package dk.itcamp.taxicamp.listeners;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.View;
import android.widget.Toast;

import dk.itcamp.taxicamp.standard.Taxi;

public class OrderTaxiOnClickListener implements View.OnClickListener  {

    Taxi taxi;

    public OrderTaxiOnClickListener(Taxi taxi) {
        this.taxi = taxi;
    }

    @Override
    public void onClick(View v) {
        new AlertDialog.Builder(v.getContext())
                .setTitle("Title")
                .setMessage("Do you really want to whatever?")
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setPositiveButton(android.R.string.yes, new OnConfirmTaxiListener(v, this.taxi))
                .setNegativeButton(android.R.string.no, null).show();
    }
}
