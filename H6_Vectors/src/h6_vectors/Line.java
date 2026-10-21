package h6_vectors;

import java.awt.Color;
import java.awt.geom.Line2D;

public class Line extends Shape {
    public Line(int x, int y, int width, int height, boolean selected, Color color, boolean filled) {
        super(x, y, width, height, selected, color, false);
    }

    @Override
    public Line2D get2DShape() {
        return new Line2D.Double(x, y, x + width, y + height);
    }

    @Override
    public double distanceFrom(int px, int py) {
        // Java's point-to-segment distance also handles the two endpoints.
        return get2DShape().ptSegDist(px, py);
    }

    @Override
    public String toSvg() {
        return "<line x1=\"" + x + "\" y1=\"" + y + "\" x2=\"" + (x + width)
                + "\" y2=\"" + (y + height) + "\"" + svgStyle() + "/>";
    }
}
