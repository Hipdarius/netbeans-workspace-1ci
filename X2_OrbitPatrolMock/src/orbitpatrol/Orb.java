package orbitpatrol;

import java.awt.Color;
import java.awt.Graphics;

/** Coordinates are the CENTRE of the circle; vx/vy are pixels per timer tick. */
public abstract class Orb {
    protected int x, y, vx, vy, radius;
    protected Color color;
    private boolean inactive;

    public Orb(int x, int y, int vx, int vy, int radius, Color color) {
        // TODO C1_ORB_CONSTRUCTOR
    }

    public abstract void move(int width, int height);

    public void draw(Graphics g) {
        // TODO C1_DRAW
    }

    public boolean collidesWith(Orb other) {
        // TODO C1_COLLISION
        return false;
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
