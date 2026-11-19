package boardclub;

import java.awt.Color;
import java.awt.Graphics;

public class SquareMarker extends Marker {
    public SquareMarker(Color fillColor, Color outlineColor) {
        super(fillColor, outlineColor);
    }

    @Override
    public void draw(Graphics g, int x, int y, int cellSize) {
        int inset = cellSize / 5;
        int side = cellSize - 2 * inset - 1;
        g.setColor(fillColor);
        g.fillRect(x + inset, y + inset, side, side);
        g.setColor(outlineColor);
        g.drawRect(x + inset, y + inset, side, side);
    }
}
