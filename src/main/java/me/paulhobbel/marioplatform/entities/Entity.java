package me.paulhobbel.marioplatform.entities;

import me.paulhobbel.engine.Engine;
import me.paulhobbel.engine.objects.AnimatedSprite;
import me.paulhobbel.engine.physics.box2d.Fixture;
import me.paulhobbel.engine.physics.box2d.Geometry;
import me.paulhobbel.engine.physics.collisionOld.Collidable;
import org.jbox2d.dynamics.BodyType;

import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class Entity<T> extends AnimatedSprite<T> {
    public Entity(Point2D position, int layer, String spriteFile, int rows, int columns) {
        super(position, layer, spriteFile, rows, columns);

        body.setType(BodyType.DYNAMIC);
        Fixture fixture = body.createFixture(Geometry.createRectangle(getImage().getWidth() * 3 / Engine.PPM, getImage().getHeight() * 3 / Engine.PPM));

        fixture.setRestitution(0.1f);
        fixture.setFriction(0.1f);
    }
}
