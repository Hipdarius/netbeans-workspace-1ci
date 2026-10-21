package orbitpatrol;

import java.awt.Color;

public class Bullet extends Orb {
    public Bullet(int x, int y, int vx, int vy, int radius) {
        super(x, y, vx, vy, radius, new Color(245, 185, 65));
    }

    @Override
    public void move(int width, int height) {
        x += vx;
        y += vy;
        
        if(x + radius < 0 || x - radius > width || y - radius > height || y + radius < 0) {
            setInactive(true);
        }
    }

    @Override
    public String toFileRecord() {
        return "BULLET;" + x + ";" + y + ";" + vx + ";" + vy + ";" + radius;
    }
}
