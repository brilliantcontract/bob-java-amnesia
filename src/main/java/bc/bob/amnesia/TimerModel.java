package bc.bob.amnesia;

/**
 * Simple countdown timer model that stores remaining seconds and allows
 * start/stop/reset operations.
 */
public final class TimerModel {

    private final int initialSeconds;
    private int remainingSeconds;
    private boolean running;

    public TimerModel(final int hours, final int minutes) {
        assert hours >= 0 : "hours must be non negative, got: " + hours;
        assert minutes >= 0 : "minutes must be non negative, got: " + minutes;
        assert minutes < 60 : "minutes must be less than 60, got: " + minutes;
        this.initialSeconds = hours * 3600 + minutes * 60;
        this.remainingSeconds = this.initialSeconds;
        this.running = true;
    }

    public void start() {
        this.running = true;
    }

    public void stop() {
        this.running = false;
    }

    public void reset() {
        this.remainingSeconds = this.initialSeconds;
        this.running = true;
    }

    public void tick() {
        if (this.running && this.remainingSeconds > 0) {
            this.remainingSeconds -= 1;
        }
    }

    public int getHours() {
        return this.remainingSeconds / 3600;
    }

    public int getMinutes() {
        return (this.remainingSeconds % 3600) / 60;
    }

    public int getSeconds() {
        return this.remainingSeconds % 60;
    }
}
