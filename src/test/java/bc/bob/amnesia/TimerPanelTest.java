package bc.bob.amnesia;

import java.lang.reflect.Field;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import org.junit.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * Tests for {@link TimerPanel}.
 */
public class TimerPanelTest {

    @Test
    public void startButtonRestartsTimer() throws Exception {
        // Initialization.
        final TimerModel model = new TimerModel(0, 1);
        final JPanel container = new JPanel();
        final TimerPanel panel = new TimerPanel(model, container, "");
        final Field uiTimerField = TimerPanel.class.getDeclaredField("uiTimer");
        uiTimerField.setAccessible(true);
        final Timer timer = (Timer) uiTimerField.get(panel);
        timer.stop();
        final Field stopField = TimerPanel.class.getDeclaredField("buttonStop");
        stopField.setAccessible(true);
        final JButton buttonStop = (JButton) stopField.get(panel);
        final Field startField = TimerPanel.class.getDeclaredField("buttonStart");
        startField.setAccessible(true);
        final JButton buttonStart = (JButton) startField.get(panel);

        // Execution.
        buttonStop.doClick();
        model.tick();
        buttonStart.doClick();
        model.tick();

        // Assertion.
        assertThat(model.getMinutes(), is(0));
        assertThat(model.getSeconds(), is(59));
    }

    @Test
    public void showsNameLabelWhenNameProvided() throws Exception {
        // Initialization.
        final String name = "Test";
        final TimerModel model = new TimerModel(0, 1);
        final JPanel container = new JPanel();
        final TimerPanel panel = new TimerPanel(model, container, name);
        final Field uiTimerField = TimerPanel.class.getDeclaredField("uiTimer");
        uiTimerField.setAccessible(true);
        final Timer timer = (Timer) uiTimerField.get(panel);
        timer.stop();
        final java.awt.Component[] components = panel.getComponents();
        boolean found = false;

        // Execution.
        for (int i = 0; i < components.length; i += 1) {
            final java.awt.Component comp = components[i];
            if (comp instanceof JLabel) {
                final JLabel label = (JLabel) comp;
                if (name.equals(label.getText())) {
                    found = true;
                }
            }
        }

        // Assertion.
        assertThat(found, is(true));
    }
}
