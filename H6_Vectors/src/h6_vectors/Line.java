/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package h6_vectors;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author Darius
 */
public class Line extends Shape {

    public Line(int x, int y, int width, int height, boolean selected, Color color, boolean filled) {
        super(x, y, width, height, selected, color, filled);
    }
    
    @Override
    public void draw(Graphics g) {
        g.setColor(color);
        g.drawLine(x, y, x + width, y + height);
    }
}
