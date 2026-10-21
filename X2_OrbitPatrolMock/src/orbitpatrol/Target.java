package orbitpatrol;

import java.awt.Color;
import java.awt.Graphics;

public class Target extends Orb {
    private int points;

    public Target(int x, int y, int vx, int vy, int radius, int points) {
        super(x, y, vx, vy, radius, points == 20 ? new Color(226, 145, 191) : new Color(130, 208, 155));
        this.points = points;
    }

    @Override
    public void move(int width, int height) {
        x = x + vx;
        y = y + vy;
        
        if(x < radius) {
            x = radius;
            vx = -vx;
        } else if (x > width - radius) {
            x = width - radius;
            vx = -vx;
        }
        
        int bottom = height * 2 / 3;
        
        if (y < radius) {
            y = radius;
            vy = -vy;
        } else if (y > bottom - radius) {
            y = bottom - radius;
            vy = -vy;
        }
    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);
        g.setColor(Color.BLACK);
        g.drawString(String.valueOf(points), x - 7, y + 4);
    }

    public int getPoints() { return points; }

    @Override
    public String toFileRecord() {
        return "TARGET;" + x + ";" + y + ";" + vx + ";" + vy + ";" + radius + ";" + points;
    }
}
