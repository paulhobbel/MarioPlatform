package me.paulhobbel.marioplatform.objects;

import me.paulhobbel.engine.core.Engine;
import me.paulhobbel.engine.core.GameObject;
import me.paulhobbel.engine.physics.box2d.BodyDef;
import me.paulhobbel.engine.physics.box2d.Geometry;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.BodyType;

abstract class Interactable extends GameObject {
    Interactable(Vec2 position, int width, int height) {
        BodyDef def = new BodyDef();
        def.type = BodyType.STATIC;
        def.position.set((position.x + (width / 2)) * 3f / Engine.PPM, (position.y + (height / 2)) * 3f / Engine.PPM);
        body = world.getPhysicsWorld().createBody(def);

        body.createFixture(Geometry.createRectangle(width * 3f / Engine.PPM, height * 3f / Engine.PPM));
    }


}
