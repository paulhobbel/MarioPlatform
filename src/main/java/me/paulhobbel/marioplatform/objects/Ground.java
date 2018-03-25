package me.paulhobbel.marioplatform.objects;

import me.paulhobbel.engine.GameObject;
import me.paulhobbel.engine.component.DebugComponent;
import org.dyn4j.geometry.Geometry;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.Point2D;

public class Ground extends GameObject {
    public Ground(Point2D position, Shape shape) {
        super(position);

        addComponent(new DebugComponent(shape, Color.MAGENTA, this));

        if(shape instanceof Rectangle) {
            body.addFixture(Geometry.createRectangle(((Rectangle) shape).getWidth() / 100, ((Rectangle) shape).getHeight() / 100));
            body.getTransform().setTranslation(position.getX() / 100.0, -(position.getY() / 100.0));
        }
        setScale(3);
    }
}
