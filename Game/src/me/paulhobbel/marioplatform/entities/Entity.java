package me.paulhobbel.marioplatform.entities;

import me.paulhobbel.engine.core.Engine;
import me.paulhobbel.engine.objects.AnimatedSprite;
import me.paulhobbel.engine.physics.box2d.BodyDef;
import me.paulhobbel.engine.physics.box2d.Geometry;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.BodyType;

public class Entity<T> extends AnimatedSprite<T> {
    public Entity(Vec2 position, int layer, String spriteFile, int rows, int columns) {
        super(position, layer, spriteFile, rows, columns);

        BodyDef def = new BodyDef();
        def.type = BodyType.DYNAMIC;
        def.position.set((position.x + (getImage().getWidth() / 2)) * 3f / Engine.PPM, (position.y + (getImage().getWidth() / 2)) * 3f / Engine.PPM);
        body = world.getPhysicsWorld().createBody(def);

        body.createFixture(Geometry.createRectangle(getImage().getWidth() * 3 / Engine.PPM, getImage().getHeight() * 3 / Engine.PPM));
    }
}
