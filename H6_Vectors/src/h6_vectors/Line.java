package h6_vectors;

import java.awt.Color;
import java.awt.Graphics;

public class Line extends Shape {
    public Line(int x, int y, int width, int height, boolean selected, Color color, boolean filled) {
        super(x, y, width, height, selected, color, false);
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(color);
        g.drawLine(x, y, x + width, y + height);
        if (selected) {
            g.drawLine(x + 1, y, x + width + 1, y + height);
            g.drawLine(x - 1, y, x + width - 1, y + height);
        }
    }

    @Override
    public double distanceFrom(int px, int py) {
        return lineDistance(x, y, x + width, y + height, px, py);
    }

    @Override
    public boolean contains(int px, int py) {
        return distanceFrom(px, py) <= 5;
    }

    @Override
    public Line clone() {
        return new Line(x, y, width, height, false, color, false);
    }

    @Override
    public String toSvg() {
        return "<line x1=\"" + x + "\" y1=\"" + y + "\" x2=\"" + (x + width)
                + "\" y2=\"" + (y + height) + "\"" + svgStyle() + "/>";
    }
}
