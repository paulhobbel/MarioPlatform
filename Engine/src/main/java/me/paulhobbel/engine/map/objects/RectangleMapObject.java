package me.paulhobbel.engine.map.objects;

import me.paulhobbel.engine.map.MapObject;

import java.awt.Shape;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Rectangle2D.Double;

public class RectangleMapObject extends MapObject {

    Rectangle2D rectangle;

    public RectangleMapObject(double x, double y, double width, double height) {
        rectangle = new Double(x, y, width, height);
    }

    public Rectangle2D getRectangle() {
        return rectangle;
    }

    @Override
    public Shape getShape() {
        return rectangle;
    }
}
