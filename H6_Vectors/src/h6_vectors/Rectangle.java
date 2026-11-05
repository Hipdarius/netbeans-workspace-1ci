package h6_vectors;

import java.awt.Color;
import java.awt.Graphics;

public class Rectangle extends Shape {
    public Rectangle(int x, int y, int width, int height, boolean selected, Color color, boolean filled) {
        super(x, y, width, height, selected, color, filled);
    }

    @Override
    public void draw(Graphics g) {
        int left = Math.min(x, x + width), top = Math.min(y, y + height);
        int w = Math.abs(width), h = Math.abs(height);
        if (filled) {
            g.setColor(fillColor);
            g.fillRect(left, top, w, h);
        }
        g.setColor(color);
        g.drawRect(left, top, w, h);
        if (selected) {
            g.drawRect(left - 1, top - 1, w + 2, h + 2);
            g.drawRect(left + 1, top + 1, w - 2, h - 2);
        }
    }

    @Override
    public double distanceFrom(int px, int py) {
        int left = Math.min(x, x + width), right = Math.max(x, x + width);
        int top = Math.min(y, y + height), bottom = Math.max(y, y + height);
        if (filled && px >= left && px <= right && py >= top && py <= bottom)
            return 0;
        double d = lineDistance(left, top, right, top, px, py);
        d = Math.min(d, lineDistance(right, top, right, bottom, px, py));
        d = Math.min(d, lineDistance(right, bottom, left, bottom, px, py));
        return Math.min(d, lineDistance(left, bottom, left, top, px, py));
    }

    @Override
    public boolean contains(int px, int py) {
        return px >= Math.min(x, x + width) && px <= Math.max(x, x + width)
                && py >= Math.min(y, y + height) && py <= Math.max(y, y + height);
    }

    @Override
    public Rectangle clone() {
        Rectangle copy = new Rectangle(x, y, width, height, false, color, filled);
        copy.fillColor = fillColor;
        return copy;
    }

    @Override
    public String toSvg() {
        if (width == 0 || height == 0)
            return "<line x1=\"" + x + "\" y1=\"" + y + "\" x2=\"" + (x + width)
                    + "\" y2=\"" + (y + height) + "\"" + svgStyle() + "/>";
        return "<rect x=\"" + Math.min(x, x + width) + "\" y=\"" + Math.min(y, y + height)
                + "\" width=\"" + Math.abs(width) + "\" height=\"" + Math.abs(height)
                + "\"" + svgStyle() + "/>";
    }
}
