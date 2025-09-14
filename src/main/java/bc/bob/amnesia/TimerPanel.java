package bc.bob.amnesia;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.util.Objects;

/**
 * Panel that displays a countdown timer and controls.
 */
public final class TimerPanel extends JPanel {

    private final TimerModel model;
    private final JLabel labelName;
    private final JLabel labelHours;
    private final JLabel labelMinutes;
    private final JLabel labelSeconds;
    private final JButton buttonDelete;
    private final JButton buttonReset;
    private final JButton buttonStop;
    private final JButton buttonStart;
    private final Timer uiTimer;
    private final JPanel container;

    public TimerPanel(final TimerModel model, final JPanel container, final String name) {
        super();
        this.model = Objects.requireNonNull(model, "model");
        this.container = Objects.requireNonNull(container, "container");
        final String timerName = Objects.requireNonNull(name, "name");
        this.labelName = new JLabel(timerName);
        this.labelHours = new JLabel("0");
        this.labelMinutes = new JLabel("0");
        this.labelSeconds = new JLabel("0");
        this.buttonDelete = new JButton("Delete");
        this.buttonReset = new JButton("Reset");
        this.buttonStop = new JButton("Stop");
        this.buttonStart = new JButton("Start");
        setLayout(new java.awt.FlowLayout());
        if (!timerName.isEmpty()) {
            add(this.labelName);
        }
        add(this.labelHours);
        add(this.labelMinutes);
        add(this.labelSeconds);
        add(this.buttonDelete);
        add(this.buttonReset);
        add(this.buttonStop);
        add(this.buttonStart);
        updateLabels();
        this.uiTimer = new Timer(1000, new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                TimerPanel.this.model.tick();
                TimerPanel.this.updateLabels();
            }
        });
        this.uiTimer.start();
        this.buttonDelete.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                TimerPanel.this.uiTimer.stop();
                TimerPanel.this.container.remove(TimerPanel.this);
                TimerPanel.this.container.revalidate();
                TimerPanel.this.container.repaint();
            }
        });
        this.buttonReset.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                TimerPanel.this.model.reset();
                TimerPanel.this.updateLabels();
            }
        });
        this.buttonStop.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                TimerPanel.this.model.stop();
                TimerPanel.this.uiTimer.stop();
            }
        });
        this.buttonStart.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                TimerPanel.this.model.start();
                TimerPanel.this.uiTimer.start();
            }
        });
    }

    /**
     * Update labels to reflect remaining time in the model.
     */
    private void updateLabels() {
        this.labelHours.setText(String.valueOf(this.model.getHours()));
        this.labelMinutes.setText(String.valueOf(this.model.getMinutes()));
        this.labelSeconds.setText(String.valueOf(this.model.getSeconds()));
    }
}
