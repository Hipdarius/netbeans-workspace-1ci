/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package h6_vectors;

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
}
