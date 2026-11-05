package h6_vectors;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

/** Run main directly; no external test library is required. */
public class DrawingTest {
    private static int checks;

    private static void check(boolean condition, String message) {
        checks++;
        if (!condition) throw new AssertionError(message);
    }

    public static void main(String[] args) {
        Drawing drawing = new Drawing();
        Rectangle rectangle = new Rectangle(80, 80, -60, -60, false, Color.RED, false);
        Oval oval = new Oval(20, 20, 60, 60, false, Color.BLUE, true);
        drawing.addShape(rectangle);
        drawing.addShape(oval);
        check(drawing.getSelected(50, 50) == oval, "Select the topmost shape");
        drawing.sendToBack();
        check(drawing.getSelected(50, 50) == rectangle, "Select hollow reversed rectangle");
        check(drawing.getSelected(200, 200) == null && !rectangle.selected,
                "Empty-space selection clears the previous selection");
        check(Drawing.fromJson(drawing.toJson()).toJson().equals(drawing.toJson()),
                "Drawing JSON round trip");
        check(rectangle.toSvg().contains("width=\"60\""), "Normalize reversed SVG dimensions");
        check(rectangle.clone() != rectangle && !rectangle.clone().selected,
                "Cloning creates an independent unselected shape");
        try {
            Drawing.fromJson("[\"Unknown|{}\"]");
            throw new AssertionError("Invalid drawing accepted");
        } catch (IllegalArgumentException expected) {
            check(drawing.getSelected(50, 50) == rectangle, "Failed load preserves original drawing");
        }
        checkLineHighlight(40, 0, 30, 9);
        checkLineHighlight(0, 40, 9, 30);
        System.out.println("DrawingTest: " + checks + " checks passed.");
    }

    private static void checkLineHighlight(int width, int height, int px, int py) {
        BufferedImage image = new BufferedImage(70, 70, BufferedImage.TYPE_INT_ARGB);
        Graphics graphics = image.getGraphics();
        try {
            Line line = new Line(10, 10, width, height, false, Color.BLACK, false);
            line.draw(graphics);
            check(image.getRGB(px, py) == 0, "Unselected line stays thin");
            line.selected = true;
            line.draw(graphics);
            check(image.getRGB(px, py) == Color.BLACK.getRGB(), "Selected line is visibly thicker");
        } finally {
            graphics.dispose();
        }
    }
}
