package orbitpatrol;

import java.awt.Color;
import java.awt.Graphics;

/** Coordinates are the CENTRE of the circle; vx/vy are pixels per timer tick. */
public abstract class Orb {
    protected int x, y, vx, vy, radius;
    protected Color color;
    private boolean inactive;

    public Orb(int x, int y, int vx, int vy, int radius, Color color) {
        this.x = x;
        this.y = y;
        this.vx = vx;
        this.vy = vy;
        this.radius = radius;
        this.color = color;
    }

    public abstract void move(int width, int height);

    public void draw(Graphics g) {
        g.setColor(color);
        g.fillOval(x - radius, y - radius, radius * 2, radius * 2);
        g.setColor(Color.BLACK);
        g.drawOval(x - radius, y - radius, radius * 2, radius * 2);
    }

    public boolean collidesWith(Orb other) {
        double dx = (double) x - other.x;
        double dy = (double) y - other.y;
        
        double distance = Math.sqrt(dx * dx + dy * dy);
        
        return distance <= radius + other.radius;
    }

    public abstract String toFileRecord(); // supplied file format in subclasses
    public int getX() { return x; }
    public int getY() { return y; }
    public int getVx() { return vx; }
    public int getVy() { return vy; }
    public int getRadius() { return radius; }
    public boolean isInactive() { return inactive; }
    public void setInactive(boolean inactive) { this.inactive = inactive; }
}
