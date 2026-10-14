package exam;
import java.awt.*;
public class PrintJob {
    private String name;
    private int duration;
    private int remaining;
    public PrintJob(String name, int duration) {
        // TODO C1
        throw new UnsupportedOperationException("TODO C1");
    }

    // Supplied accessors and simulation support
    public String getName() { return name; }
    public int getDuration() { return duration; }
    public int getRemaining() { return remaining; }
    public boolean isUrgent() { return false; }
    public void workOneMinute() { if (remaining > 0) remaining--; }
    @Override public String toString() {
        return name + " | " + duration + " min | " + (isUrgent() ? "U" : "N");
    }
    public void draw(Graphics g, int x, int y) {
        // TODO C1
    }
}
