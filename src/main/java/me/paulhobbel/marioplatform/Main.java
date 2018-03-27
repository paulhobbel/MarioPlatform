package me.paulhobbel.marioplatform;

import me.paulhobbel.engine.Engine;
import me.paulhobbel.engine.GameWorld;
import me.paulhobbel.engine.physics.box2d.Body;
import me.paulhobbel.engine.physics.box2d.BodyDef;
import me.paulhobbel.engine.physics.box2d.FixtureDef;
import me.paulhobbel.engine.window.WindowManager;
import me.paulhobbel.marioplatform.entities.Player;
import me.paulhobbel.marioplatform.maps.Level1;
import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Settings;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.BodyType;

import java.awt.*;
import java.awt.geom.Point2D;

public class Main {
    public static void main(String[] args) {
        new Main();
    }

    public Main() {
        WindowManager.getInstance().createWindow(new Dimension(816, 672), "Test");

        Engine engine = Engine.getInstance();
        GameWorld world = engine.getWorld();

//        world.addObject(new Level1());
//        world.addObject(new Player(new Point2D.Double(40, 160)));
        BodyDef bdef = new BodyDef();
        bdef.position.set(250/3, 50/3);
        bdef.type = BodyType.DYNAMIC;
        //bdef.fixedRotation = false;
        //bdef.allowSleep = false;
        Body body = world.getPhysicsWorld().createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(5);

        fdef.shape = shape;
        fdef.restitution = 0.6f;
        fdef.friction = 0.6f;
        fdef.density = 0.1f;
        body.createFixture(fdef);

        BodyDef floordef = new BodyDef();
        floordef.position.set(80+50/3, 400/3);
        floordef.type = BodyType.STATIC;
        floordef.angle = 0.1f;
        Body floorBody = world.getPhysicsWorld().createBody(floordef);

        FixtureDef floorFixtureDef = new FixtureDef();
        PolygonShape shape1 = new PolygonShape();
        shape1.setAsBox(500/3, 10/3);

        floorFixtureDef.shape = shape1;
        floorBody.createFixture(floorFixtureDef);

        Settings.maxPolygonVertices = 50;

        engine.start();
    }
}