package dk.itcamp.taxicamp.runnable;

import android.view.View;
import android.widget.Toast;


public class TaxiHasArrivedRunner implements Runnable {

    private View view;

    public TaxiHasArrivedRunner(View view) {
        this.view = view;
    }

    private String taxiIsWaitingForYouMessage() {
        return "Din taxi venter på dig nu.";
    }

    @Override
    public void run() {
        Toast.makeText(this.view.getContext(), this.taxiIsWaitingForYouMessage(), Toast.LENGTH_LONG).show();
    }
}
