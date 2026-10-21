/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package h6_vectors;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializer;
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

    @Override
    public String toString() {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Color.class, (JsonSerializer<Color>)
                        (value, type, context) -> new JsonPrimitive(value.getRGB()))
                .create();
        return getClass().getSimpleName() + "|" + gson.toJson(this);
    }

    public static Shape fromString(String s) {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Color.class, (JsonDeserializer<Color>)
                        (value, type, context) -> new Color(value.getAsInt(), true))
                .create();

        String[] pieces = s.split("\\|", 2);
        String classname = pieces[0];
        String json = pieces[1];

        if (classname.equals("Line"))
            return gson.fromJson(json, Line.class);
        else if (classname.equals("Rectangle"))
            return gson.fromJson(json, Rectangle.class);
        else if (classname.equals("Oval"))
            return gson.fromJson(json, Oval.class);

        return null;
    }
}
