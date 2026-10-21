/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package h6_vectors;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.awt.Graphics;
import java.util.ArrayList;

/**
 *
 * @author Darius
 */
public class Drawing {
    private final ArrayList<Shape> shapes = new ArrayList<>();
    
    public void addShape(Shape shape) {
        shapes.add(shape);
    }

    public void draw(Graphics g) {
        for (Shape shape : shapes) {
            shape.draw(g);
        }
    }

    public String toJson() {
        ArrayList<String> savedShapes = new ArrayList<>();
        for (Shape shape : shapes) {
            savedShapes.add(shape.toString());
        }
        return new GsonBuilder().setPrettyPrinting().create().toJson(savedShapes);
    }

    public static Drawing fromJson(String json) {
        String[] savedShapes = new Gson().fromJson(json, String[].class);
        if (savedShapes == null) {
            throw new IllegalArgumentException("This file does not contain a drawing.");
        }

        Drawing drawing = new Drawing();
        for (String savedShape : savedShapes) {
            Shape shape = Shape.fromString(savedShape);
            if (shape == null) {
                throw new IllegalArgumentException("Unknown shape in the file.");
            }
            drawing.addShape(shape);
        }
        return drawing;
    }
}
