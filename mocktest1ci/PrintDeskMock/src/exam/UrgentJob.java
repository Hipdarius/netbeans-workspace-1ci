package exam;
import java.awt.*;
public class UrgentJob extends PrintJob {
    // Constructor is supplied; reuse the parent's validation and state.
    public UrgentJob(String name, int duration) { super(name,duration); }
    @Override public boolean isUrgent() {
        // TODO C1
        return false;
    }
    @Override public void draw(Graphics g, int x, int y) {
        // TODO C1
    }
}
