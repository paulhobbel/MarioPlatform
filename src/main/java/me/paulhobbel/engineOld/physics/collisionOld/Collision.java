package me.paulhobbel.engineOld.physics.collisionOld;

import java.awt.*;
import java.awt.geom.Point2D;

public class Collision {
    /**
     * Check whether a point lives inside of a collidable
     * @param collidable The collidable to check in
     * @param point The point
     */
    public static boolean isPointInside(Collidable collidable, Point2D point) {
        Rectangle box = collidable.getBounds().getBounds();

        return box.x <= point.getX() && point.getX() <= box.x + box.width &&
                box.y <= point.getY() && point.getY() <= box.y + box.height;
    }

    public static boolean isOverlappting(Collidable collidableA, Collidable collidableB) {
        Rectangle boxA = collidableA.getBounds().getBounds();
        Rectangle boxB = collidableB.getBounds().getBounds();

        Point2D dxy = new Point2D.Double(boxB.x - boxA.x, boxB.y - boxA.y);

        return false;
    }
}
