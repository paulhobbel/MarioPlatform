package me.paulhobbel.marioplatform.objects;

import me.paulhobbel.engine.GameObject;
import me.paulhobbel.engine.component.DebugComponent;

import java.awt.Color;
import java.awt.Shape;
import java.awt.geom.Point2D;

public class Ground extends GameObject {
    public Ground(Point2D position, Shape shape) {
        super(position);

        addComponent(new DebugComponent(shape, Color.MAGENTA, this));
        setScale(3);
    }
}
