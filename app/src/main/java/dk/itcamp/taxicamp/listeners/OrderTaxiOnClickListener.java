package dk.itcamp.taxicamp.listeners;

import android.app.Activity;
import android.app.AlertDialog;
import android.view.View;

import dk.itcamp.taxicamp.R;
import dk.itcamp.taxicamp.standard.Taxi;

public class OrderTaxiOnClickListener implements View.OnClickListener  {

    private Taxi taxi;
    private Activity activity;

    public OrderTaxiOnClickListener(Activity activity, Taxi taxi) {
        this.taxi = taxi;
        this.activity = activity;
    }

    private String titleForAlertDialog() {
        return "Vil du gerne bestille en taxi?";
    }

    private String messageForAlertDialog() {
        return "Er du sikker på du vil bestille en taxi?";
    }

    @Override
    public void onClick(View view) {
        new AlertDialog.Builder(view.getContext(),  R.style.Theme_AppCompat)
                .setTitle(this.titleForAlertDialog())
                .setMessage(this.messageForAlertDialog())
                .setIcon(android.R.drawable.ic_menu_directions)
                .setPositiveButton("Ja", new OnConfirmTaxiListener(view, this.taxi, this.activity))
                .setNegativeButton("Nej", null).show();
    }
}
