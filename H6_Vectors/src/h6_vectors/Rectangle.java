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
public class Rectangle extends Shape {

    public Rectangle(int x, int y, int width, int height, boolean selected, Color color, boolean filled) {
        super(x, y, width, height, selected, color, filled);
    }
    
    @Override
    public void draw(Graphics g) {
        int left = Math.min(x, x + width);
        int top = Math.min(y, y + height);
        int actualWidth = Math.abs(width);
        int actualHeight = Math.abs(height);

        g.setColor(color);
        
        if (filled) {
            g.fillRect(left, top, actualWidth, actualHeight);
        }
        
        g.drawRect(left, top, actualWidth, actualHeight);
    }
}
