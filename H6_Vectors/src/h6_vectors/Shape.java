package h6_vectors;

import com.google.gson.Gson;
import java.awt.Color;
import java.awt.Graphics;

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

    public abstract void draw(Graphics g);
    public abstract double distanceFrom(int px, int py);
    public abstract boolean contains(int px, int py);
    public abstract Shape clone();
    public abstract String toSvg();

    protected void drawHandle(Graphics g) {
        if (selected) {
            g.setColor(Color.BLUE);
            g.fillRect(x + width - 4, y + height - 4, 8, 8);
        }
    }

    protected double lineDistance(double x1, double y1, double x2, double y2, int px, int py) {
        double dx = x2 - x1;
        double dy = y2 - y1;
        double lengthSquared = dx * dx + dy * dy;
        if (lengthSquared == 0) return Math.hypot(px - x1, py - y1);

        double position = ((px - x1) * dx + (py - y1) * dy) / lengthSquared;
        if (position < 0) position = 0;
        if (position > 1) position = 1;

        double nearestX = x1 + position * dx;
        double nearestY = y1 + position * dy;
        return Math.hypot(px - nearestX, py - nearestY);
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
        String className = pieces[0];
        String json = pieces[1];
        
        ShapeData data = new Gson().fromJson(json, ShapeData.class);
        if (data == null || data.color == null) {
            throw new IllegalArgumentException("The shape has no color.");
        }

        Color color = new Color(data.color, true);
        Shape shape;

        if (className.equals("Line")) {
            shape = new Line(data.x, data.y, data.width, data.height, false, color, false);
        } else if (className.equals("Rectangle")) {
            shape = new Rectangle(data.x, data.y, data.width, data.height, false, color, data.filled);
        } else if (className.equals("Oval")) {
            shape = new Oval(data.x, data.y, data.width, data.height, false, color, data.filled);
        } else {
            throw new IllegalArgumentException("Unknown shape: " + className);
        }


        if (data.fillColor != null) {
            shape.fillColor = new Color(data.fillColor, true);
        }
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
