package dk.itcamp.taxicamp.listeners;

import android.app.Activity;
import android.app.AlertDialog;
import android.view.View;

import java.math.BigDecimal;

import dk.itcamp.taxicamp.R;
import dk.itcamp.taxicamp.standard.Taxi;

public class OrderTaxiOnClickListener implements View.OnClickListener  {

    private Taxi taxi;
    private Activity activity;
    private BigDecimal distance;

    public OrderTaxiOnClickListener(Activity activity, Taxi taxi, BigDecimal distance) {
        this.taxi = taxi;
        this.activity = activity;
        this.distance = distance;
    }

    private String titleForAlertDialog() {
        return "Er du sikker på at du gerne vil bestille en taxi?";
    }

    private String messageForAlertDialog() {
        return "Det er en distance på "+ this.distance + " og det koster 20 kr pr. kilometer, det vil sige totalt: "
                + this.distance.multiply(BigDecimal.valueOf(20)).setScale(2, BigDecimal.ROUND_CEILING) + ",-";
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
