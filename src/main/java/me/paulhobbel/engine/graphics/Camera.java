package me.paulhobbel.engine.graphics;

import me.paulhobbel.engine.window.WindowPanel;

import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

public class Camera {

    private Point2D position = new Point2D.Double(0, 0);
    private float zoom = 1;

    public Camera() { }

    public Point2D getPosition() {
        return position;
    }

    public void setPosition(Point2D position) {
        this.position = position;
    }

    public float getZoom() {
        return zoom;
    }

    public void setZoom(float zoom) {
        this.zoom = zoom;
    }

    public AffineTransform getTransform() {
        AffineTransform tx = new AffineTransform();
        //tx.translate(panel.getWidth() / 2, panel.getHeight() / 2);
        tx.scale(zoom, zoom);
        tx.translate(-position.getX(), -position.getY());

        return tx;
    }
}
