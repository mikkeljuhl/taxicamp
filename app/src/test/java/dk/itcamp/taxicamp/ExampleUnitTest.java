package dk.itcamp.taxicamp;

import org.junit.Test;

import java.math.BigDecimal;

import dk.itcamp.taxicamp.listeners.OnConfirmTaxiListener;
import dk.itcamp.taxicamp.runnable.TaxiRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }


    @Test
    public void findTheCorrectNumberOfSteps() {
        TaxiRunner t = new TaxiRunner(null);
        assertThat(t.locationEquality(37.421, 37.421998333333335), is(false));
    }
    @Test
    public void findTheCorrectNumberOfSteps2() {
        TaxiRunner t = new TaxiRunner(null);
        assertThat(t.locationEquality(-122.083, -122.08400000000002), is(false));
    }
    @Test
    public void findTheCorrectNumberOfSteps3() {
        TaxiRunner t = new TaxiRunner(null);
        assertThat(t.locationEquality(37.422, 37.421998333333335), is(true));
    }
    @Test
    public void findTheCorrectNumberOfSteps4() {
        TaxiRunner t = new TaxiRunner(null);
        assertThat(t.locationEquality(-122.084, -122.08400000000002), is(true));
    }

    @Test
    public void testThatWeFindCorrectNumberOfLoops() {
        OnConfirmTaxiListener c = new OnConfirmTaxiListener(null, null, null);
        assertThat(c.findAmountOfLoops(BigDecimal.valueOf( -0.04100000000002)), is(42));
    }
}