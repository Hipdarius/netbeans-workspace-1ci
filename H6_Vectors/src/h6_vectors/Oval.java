package h6_vectors;

import java.awt.Color;
import java.awt.Graphics;

public class Oval extends Shape {
    public Oval(int x, int y, int width, int height, boolean selected, Color color, boolean filled) {
        super(x, y, width, height, selected, color, filled);
    }

    @Override
    public void draw(Graphics g) {
        int left = Math.min(x, x + width), top = Math.min(y, y + height);
        int w = Math.abs(width), h = Math.abs(height);
        if (filled) {
            g.setColor(fillColor);
            g.fillOval(left, top, w, h);
        }
        g.setColor(color);
        if (w == 0 || h == 0) {
            g.drawLine(x, y, x + width, y + height);
        } else {
            g.drawOval(left, top, w, h);
            if (selected) {
                g.drawOval(left - 1, top - 1, w + 2, h + 2);
                g.drawOval(left + 1, top + 1, w - 2, h - 2);
            }
        }
        drawHandle(g);
    }

    @Override
    public double distanceFrom(int px, int py) {
        if (width == 0 || height == 0)
            return lineDistance(x, y, x + width, y + height, px, py);
        double cx = x + width / 2.0, cy = y + height / 2.0;
        double rx = Math.abs(width) / 2.0, ry = Math.abs(height) / 2.0;
        double dx = (px - cx) / rx, dy = (py - cy) / ry;
        if (filled && dx * dx + dy * dy <= 1) return 0;
        double previousX = cx + rx, previousY = cy;
        double distance = Double.MAX_VALUE;
        // Approximate the edge with short segments: x=cx+rx*cos(a), y=cy+ry*sin(a).
        for (int degrees = 1; degrees <= 360; degrees++) {
            double angle = Math.toRadians(degrees);
            double nextX = cx + rx * Math.cos(angle);
            double nextY = cy + ry * Math.sin(angle);
            distance = Math.min(distance,
                    lineDistance(previousX, previousY, nextX, nextY, px, py));
            previousX = nextX;
            previousY = nextY;
        }
        return distance;
    }

    @Override
    public String toSvg() {
        if (width == 0 || height == 0)
            return "<line x1=\"" + x + "\" y1=\"" + y + "\" x2=\"" + (x + width)
                    + "\" y2=\"" + (y + height) + "\"" + svgStyle() + "/>";
        return "<ellipse cx=\"" + (x + width / 2.0) + "\" cy=\"" + (y + height / 2.0)
                + "\" rx=\"" + Math.abs(width) / 2.0 + "\" ry=\"" + Math.abs(height) / 2.0
                + "\"" + svgStyle() + "/>";
    }
}
