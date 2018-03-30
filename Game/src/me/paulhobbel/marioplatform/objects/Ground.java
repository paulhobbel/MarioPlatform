package me.paulhobbel.marioplatform.objects;

import me.paulhobbel.engine.core.Engine;
import me.paulhobbel.engine.core.GameObject;
import me.paulhobbel.engine.physics.box2d.BodyDef;
import me.paulhobbel.engine.physics.box2d.Geometry;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.BodyType;

import java.awt.*;

public class Ground extends GameObject {
    public Ground(Vec2 position, Shape shape) {
        super(position);

        Rectangle rect = shape.getBounds();

        System.out.println(rect);

        BodyDef def = new BodyDef();
        def.type = BodyType.STATIC;
        def.position.set((position.x + (rect.width / 2)) * 3f / Engine.PPM, (position.y + (rect.height / 2)) * 3f / Engine.PPM);
        body = world.getPhysicsWorld().createBody(def);

        body.createFixture(Geometry.createRectangle(rect.width * 3f / Engine.PPM, rect.height * 3f / Engine.PPM));
    }
}
