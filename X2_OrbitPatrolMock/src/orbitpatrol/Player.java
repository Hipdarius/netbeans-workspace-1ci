package orbitpatrol;

import java.awt.Color;
import java.awt.Graphics;

/** SUPPLIED: one player is stored separately from the list of targets/bullets. */
public class Player extends Orb {
    public Player(int x, int y) {
        super(x, y, 0, 0, 19, new Color(87, 154, 211));
    }

    public void setPosition(int mouseX, int width, int height) {
        x = Math.max(radius, Math.min(width - radius, mouseX));
        y = height - 25;
    }

    @Override public void move(int width, int height) { setPosition(x, width, height); }

    @Override public void draw(Graphics g) {
        super.draw(g);
        g.setColor(new Color(44, 100, 160));
        g.fillRect(x - 4, y - radius - 12, 8, 15);
    }

    @Override public String toFileRecord() {
        throw new UnsupportedOperationException("Player is saved in the STATE record");
    }
}
