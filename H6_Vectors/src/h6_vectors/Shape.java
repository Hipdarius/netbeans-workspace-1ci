package h6_vectors;

import com.google.gson.Gson;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public abstract class Shape {
    protected int x, y, width, height;
    protected boolean selected;
    protected Color color;
    protected boolean filled;
    protected Color fillColor;

    public Shape(int x, int y, int width, int height, boolean selected, Color color, boolean filled) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.selected = selected;
        this.color = color;
        this.filled = filled;
        this.fillColor = color;
    }

    public abstract java.awt.Shape get2DShape();
    public abstract double distanceFrom(int px, int py);
    public abstract String toSvg();

    public void draw(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        java.awt.Shape outline = get2DShape();
        if (filled && !(this instanceof Line)) {
            g2.setColor(fillColor);
            g2.fill(outline);
        }
        g2.setColor(color);
        g2.draw(outline);
        if (selected) {
            g2.setColor(Color.BLUE);
            g2.drawRect(Math.min(x, x + width) - 3, Math.min(y, y + height) - 3,
                    Math.abs(width) + 6, Math.abs(height) + 6);
            // Drag this little square to resize.
            g2.fillRect(x + width - 4, y + height - 4, 8, 8);
        }
    }

    public void move(int dx, int dy) {
        x += dx;
        y += dy;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "|" + new Gson().toJson(new ShapeData(this));
    }

    public static Shape fromString(String s) {
        if (s == null || !s.contains("|")) {
            throw new IllegalArgumentException("Invalid saved shape.");
        }
        String[] pieces = s.split("\\|", 2);
        ShapeData data = new Gson().fromJson(pieces[1], ShapeData.class);
        if (data == null || data.color == null) {
            throw new IllegalArgumentException("The shape has no color.");
        }
        Color color = new Color(data.color, true);
        Shape shape;
        if (pieces[0].equals("Line"))
            shape = new Line(data.x, data.y, data.width, data.height, false, color, false);
        else if (pieces[0].equals("Rectangle"))
            shape = new Rectangle(data.x, data.y, data.width, data.height, false, color, data.filled);
        else if (pieces[0].equals("Oval"))
            shape = new Oval(data.x, data.y, data.width, data.height, false, color, data.filled);
        else
            throw new IllegalArgumentException("Unknown shape: " + pieces[0]);

        // Older files used the outline color for the filling too.
        if (data.fillColor != null)
            shape.fillColor = new Color(data.fillColor, true);
        return shape;
    }

    protected String svgStyle() {
        String stroke = String.format("#%06x", color.getRGB() & 0xffffff);
        String fill = "none";
        if (filled && !(this instanceof Line))
            fill = String.format("#%06x", fillColor.getRGB() & 0xffffff);
        return " stroke=\"" + stroke + "\" fill=\"" + fill
                + "\" stroke-opacity=\"" + color.getAlpha() / 255.0
                + "\" fill-opacity=\"" + fillColor.getAlpha() / 255.0 + "\"";
    }
}
