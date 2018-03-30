package me.paulhobbel.engineOld.physics.collisionOld;

import java.awt.Shape;
import java.awt.geom.Point2D;
import java.util.Comparator;

public interface Collidable<T extends Shape> {
    T getBounds();

    Point2D getPosition();

    static Comparator<Collidable> compareDistance(Point2D position) {
        return (Comparator.comparingDouble(o -> o.getPosition().distance(position)));
    }
}