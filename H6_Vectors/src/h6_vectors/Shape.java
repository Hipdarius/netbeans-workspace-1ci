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
public abstract class Shape {
    
    protected int x;
    protected int y;
    protected int width;
    protected int height;
    protected boolean selected;
    protected Color color;
    protected boolean filled;

    public Shape(int x, int y, int width, int height, boolean selected, Color color, boolean filled) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.selected = selected;
        this.color = color;
        this.filled = filled;
    }

    public abstract void draw(Graphics g);
}
