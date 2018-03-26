package me.paulhobbel.marioplatform.objects;

import me.paulhobbel.engine.GameObject;
import me.paulhobbel.engine.component.DebugComponent;
import me.paulhobbel.engine.physics.Collidable;

import java.awt.Color;
import java.awt.Shape;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class Ground extends GameObject implements Collidable {

    private Shape shape;

    public Ground(Point2D position, Shape shape) {
        super(position);

        this.shape = shape;

        addComponent(new DebugComponent(shape, Color.MAGENTA, this));
        setScale(3);
    }

    @Override
    public Shape getBounds() {
        return shape;
    }
}
