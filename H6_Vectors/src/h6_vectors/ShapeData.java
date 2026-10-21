package h6_vectors;

// Only simple data goes into JSON. Color objects stay in Shape.
public class ShapeData {
    int x, y, width, height;
    Integer color, fillColor;
    boolean filled;

    public ShapeData(Shape shape) {
        x = shape.x;
        y = shape.y;
        width = shape.width;
        height = shape.height;
        color = shape.color.getRGB();
        fillColor = shape.fillColor.getRGB();
        filled = shape.filled;
    }
}
