package h6_vectors;

import java.awt.Color;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

public class Rectangle extends Shape {
    public Rectangle(int x, int y, int width, int height, boolean selected, Color color, boolean filled) {
        super(x, y, width, height, selected, color, filled);
    }

    @Override
    public Rectangle2D get2DShape() {
        return new Rectangle2D.Double(Math.min(x, x + width), Math.min(y, y + height),
                Math.abs(width), Math.abs(height));
    }

    @Override
    public double distanceFrom(int px, int py) {
        Rectangle2D r = get2DShape();
        if (filled && r.contains(px, py))
            return 0;
        double left = r.getMinX(), right = r.getMaxX();
        double top = r.getMinY(), bottom = r.getMaxY();
        double d = Line2D.ptSegDist(left, top, right, top, px, py);
        d = Math.min(d, Line2D.ptSegDist(right, top, right, bottom, px, py));
        d = Math.min(d, Line2D.ptSegDist(right, bottom, left, bottom, px, py));
        return Math.min(d, Line2D.ptSegDist(left, bottom, left, top, px, py));
    }

    @Override
    public String toSvg() {
        Rectangle2D r = get2DShape();
        return "<rect x=\"" + r.getX() + "\" y=\"" + r.getY()
                + "\" width=\"" + r.getWidth() + "\" height=\"" + r.getHeight()
                + "\"" + svgStyle() + "/>";
    }
}
