package h6_vectors;

import java.awt.Color;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;

public class Oval extends Shape {
    public Oval(int x, int y, int width, int height, boolean selected, Color color, boolean filled) {
        super(x, y, width, height, selected, color, filled);
    }

    @Override
    public java.awt.Shape get2DShape() {
        if (width == 0 || height == 0)
            return new Line2D.Double(x, y, x + width, y + height);
        return new Ellipse2D.Double(Math.min(x, x + width), Math.min(y, y + height),
                Math.abs(width), Math.abs(height));
    }

    @Override
    public double distanceFrom(int px, int py) {
        if (width == 0 || height == 0)
            return Line2D.ptSegDist(x, y, x + width, y + height, px, py);
        if (filled && get2DShape().contains(px, py))
            return 0;
        double cx = x + width / 2.0, cy = y + height / 2.0;
        double rx = Math.abs(width) / 2.0, ry = Math.abs(height) / 2.0;
        double previousX = cx + rx, previousY = cy;
        double distance = Double.MAX_VALUE;
        // Approximate the edge with short segments: x=cx+rx*cos(a), y=cy+ry*sin(a).
        for (int degrees = 1; degrees <= 360; degrees++) {
            double angle = Math.toRadians(degrees);
            double nextX = cx + rx * Math.cos(angle);
            double nextY = cy + ry * Math.sin(angle);
            distance = Math.min(distance,
                    Line2D.ptSegDist(previousX, previousY, nextX, nextY, px, py));
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
