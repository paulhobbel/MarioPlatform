package me.paulhobbel.engine.map.objects;

import me.paulhobbel.engine.map.MapObject;

import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Ellipse2D.Double;

public class EllipseMapObject extends MapObject {

    private Ellipse2D ellipse;

    public EllipseMapObject(double x, double y, double width, double height) {
        ellipse = new Double(x, y, width, height);
    }

    public Ellipse2D getEllipse() {
        return ellipse;
    }

    @Override
    public Shape getShape() {
        return ellipse;
    }
}
