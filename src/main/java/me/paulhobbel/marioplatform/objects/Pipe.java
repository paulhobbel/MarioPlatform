package me.paulhobbel.marioplatform.objects;

import me.paulhobbel.engine.Engine;
import me.paulhobbel.engine.GameObject;
import me.paulhobbel.engine.component.DebugComponent;
import me.paulhobbel.engine.physics.box2d.Body;
import me.paulhobbel.engine.physics.box2d.BodyDef;
import me.paulhobbel.engine.physics.box2d.Geometry;
import me.paulhobbel.engine.physics.collisionOld.Collidable;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.BodyType;

import java.awt.*;
import java.awt.geom.Point2D;

public class Pipe extends GameObject {

    private Shape shape;

    public Pipe(Point2D position, Shape shape) {
        super(position);

        Rectangle rect = shape.getBounds();

        BodyDef def = new BodyDef();
        def.type = BodyType.STATIC;
        def.position.set(new Vec2((rect.x + (rect.width / 2)) * 3 / Engine.PPM, (rect.y + (rect.height / 2)) * 3 / Engine.PPM));

        Body body = world.getPhysicsWorld().createBody(def);
        body.createFixture(Geometry.createRectangle(rect.width * 3f / Engine.PPM, rect.height * 3f / Engine.PPM));
    }
}
