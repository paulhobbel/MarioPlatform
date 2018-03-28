package me.paulhobbel.marioplatform.objects;

import me.paulhobbel.engine.Engine;
import me.paulhobbel.engine.GameObject;
import me.paulhobbel.engine.physics.box2d.Body;
import me.paulhobbel.engine.physics.box2d.BodyDef;
import me.paulhobbel.engine.physics.box2d.Geometry;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.BodyType;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.Arrays;

public class Ground extends GameObject {
    public Ground(Point2D position, Shape shape) {
        super(position);

        Rectangle rect = shape.getBounds();

        BodyDef def = new BodyDef();
        def.type = BodyType.STATIC;
        def.position.set(((float) position.getX() + (rect.width / 2)) * 3f / Engine.PPM, ((float) position.getY() + (rect.height / 2)) * 3f / Engine.PPM);
        Body body = world.getPhysicsWorld().createBody(def);

        if(shape instanceof Polygon) {
            Polygon polygon = (Polygon) shape;

            Vec2[] vertices = new Vec2[polygon.npoints];
            for(int  i = 0; i < vertices.length; i++) {
                vertices[i] = new Vec2(polygon.xpoints[i] * 3f / Engine.PPM, polygon.ypoints[i] * 3f / Engine.PPM);
            }

            body.createFixture(Geometry.createPolygon(vertices));
        } else {
            body.createFixture(Geometry.createRectangle(rect.width * 3f / Engine.PPM, rect.height * 3f / Engine.PPM));
        }
    }
}
