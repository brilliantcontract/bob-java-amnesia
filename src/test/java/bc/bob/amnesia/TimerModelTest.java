package bc.bob.amnesia;

import org.junit.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * Tests for {@link TimerModel}.
 */
public class TimerModelTest {

    @Test
    public void tickReducesRemainingTime() {
        // Initialization.
        final int minutes = 1;
        final TimerModel model = new TimerModel(0, minutes);

        // Execution.
        model.tick();

        // Assertion.
        assertThat(model.getMinutes(), is(0));
        assertThat(model.getSeconds(), is(59));
    }

    @Test
    public void resetRestoresInitialTime() {
        // Initialization.
        final int minutes = 1;
        final TimerModel model = new TimerModel(0, minutes);

        // Execution.
        model.tick();
        model.reset();

        // Assertion.
        assertThat(model.getMinutes(), is(1));
        assertThat(model.getSeconds(), is(0));
    }
}
