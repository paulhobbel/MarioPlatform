package me.paulhobbel.marioplatform.objects;

import me.paulhobbel.engine.GameObject;
import me.paulhobbel.engine.component.DebugComponent;
import me.paulhobbel.engine.physics.collisionOld.Collidable;

import java.awt.Color;
import java.awt.Shape;
import java.awt.geom.Point2D;

public class Pipe extends GameObject implements Collidable {

    private Shape shape;

    public Pipe(Point2D position, Shape shape) {
        super(position);

        this.shape = shape;

        addComponent(new DebugComponent(shape, Color.GREEN, this));
        setScale(3);
    }

    @Override
    public Shape getBounds() {
        return shape;
    }
}
