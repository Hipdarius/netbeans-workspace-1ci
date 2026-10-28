package h6_vectors;

import com.google.gson.Gson;
import java.awt.Graphics;
import java.util.ArrayList;

public class Drawing {
    private final ArrayList<Shape> shapes = new ArrayList<>();

    public void addShape(Shape shape) {
        shapes.add(shape);
    }

    public void draw(Graphics g) {
        for (Shape shape : shapes)
            shape.draw(g);
    }

    public void clearSelection() {
        for (Shape shape : shapes)
            shape.selected = false;
    }

    public Shape getSelected() {
        for (Shape shape : shapes)
            if (shape.selected) return shape;
        
        return null;
    }

    public Shape getSelected(int x, int y) {
        clearSelection();
        // Last drawn = on top, so search backwards.
        for (int n = shapes.size() - 1; n >= 0; n--) {
            Shape shape = shapes.get(n);
            if (shape.contains(x, y) || shape.distanceFrom(x, y) <= 5) {
                shape.selected = true;
                return shape;
            }
        }
        
        return null;
    }

    public void deleteSelected() {
        shapes.remove(getSelected());
    }

    public void bringToFront() {
        Shape shape = getSelected();
        if (shape != null) {
            shapes.remove(shape);
            shapes.add(shape);
        }
    }

    public void sendToBack() {
        Shape shape = getSelected();
        if (shape != null) {
            shapes.remove(shape);
            shapes.add(0, shape);
        }
    }

    public String toJson() {
        ArrayList<String> savedShapes = new ArrayList<>();
        
        for (Shape shape : shapes)
            savedShapes.add(shape.toString());
        return new Gson().toJson(savedShapes);
    }

    public static Drawing fromJson(String json) {
        String[] savedShapes = new Gson().fromJson(json, String[].class);
        
        if (savedShapes == null)
            throw new IllegalArgumentException("This file does not contain a drawing.");
        
        Drawing drawing = new Drawing();
        
        for (String savedShape : savedShapes)
            drawing.addShape(Shape.fromString(savedShape));
        return drawing;
    }

    public String toSvg(int width, int height) {
        String text = "<svg xmlns=\"http://www.w3.org/2000/svg\" width=\"" + width
                + "\" height=\"" + height + "\" viewBox=\"0 0 " + width + " " + height + "\">\n";
        
        for (Shape shape : shapes)
            text += "  " + shape.toSvg() + "\n";
        
        return text + "</svg>\n";
    }
}
