package me.paulhobbel.engine.map.objects;

import me.paulhobbel.engine.map.MapObject;

import java.awt.Shape;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;

public class PointMapObject extends MapObject {
    Point2D point;

    public PointMapObject(double x, double y) {
        point = new Double(x, y);
    }

    public Point2D getPoint() {
        return point;
    }

    @Override
    public Shape getShape() {
        return null;
    }
}
