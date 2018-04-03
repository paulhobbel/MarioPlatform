package me.paulhobbel.marioplatform.objects;

import me.paulhobbel.engine.core.Engine;
import me.paulhobbel.engine.core.GameObject;
import me.paulhobbel.engine.physics.box2d.BodyDef;
import me.paulhobbel.engine.physics.box2d.Fixture;
import me.paulhobbel.engine.physics.box2d.Geometry;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.Filter;

import java.awt.*;

class StaticObject extends GameObject {

    protected Fixture fixture;

    StaticObject(Rectangle bounds) {
        BodyDef def = new BodyDef();
        def.type = BodyType.STATIC;
        def.position.set((bounds.x + (bounds.width / 2)) * 3f / Engine.PPM, (bounds.y + (bounds.height / 2)) * 3f / Engine.PPM);
        body = world.getPhysicsWorld().createBody(def);

        fixture = body.createFixture(Geometry.createRectangle(bounds.width * 3f / Engine.PPM, bounds.height * 3f / Engine.PPM));
    }

    protected void setCategoryFilter(short filterBit) {
        Filter filter = new Filter();
        filter.categoryBits = filterBit;
        fixture.setFilterData(filter);
    }
}
