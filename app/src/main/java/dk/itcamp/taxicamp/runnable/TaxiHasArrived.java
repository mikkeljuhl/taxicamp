package dk.itcamp.taxicamp.runnable;

import android.view.View;
import android.widget.Toast;


public class TaxiHasArrived implements Runnable {

    private View view;

    public TaxiHasArrived(View view) {
        this.view = view;
    }

    @Override
    public void run() {
        Toast.makeText(this.view.getContext(), "Din taxi venter på dig nu.", Toast.LENGTH_LONG).show();
    }
}
