package me.paulhobbel.engine.physics;

import java.awt.Shape;
import java.awt.geom.Point2D;

public interface Collidable {
    Shape getShape();

    Point2D getPosition();
}
