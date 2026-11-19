package boardclub;

import java.awt.Color;
import java.awt.Graphics;

public abstract class Marker {
    protected Color fillColor;
    protected Color outlineColor;

    public Marker(Color fillColor, Color outlineColor) {
        this.fillColor = fillColor;
        this.outlineColor = outlineColor;
    }

    public abstract void draw(Graphics g, int x, int y, int cellSize);
}
