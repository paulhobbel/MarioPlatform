package me.paulhobbel.marioplatform;

import me.paulhobbel.engine.Engine;
import me.paulhobbel.engine.GameWorld;
import me.paulhobbel.engine.physics.box2d.*;
import me.paulhobbel.engine.window.WindowManager;
import me.paulhobbel.marioplatform.entities.Player;
import me.paulhobbel.marioplatform.maps.Level1;
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

        Body body = world.getPhysicsWorld().createBody();
        body.setType(BodyType.DYNAMIC);
        body.getTransform().setTranslation(new Vec2(250/3, 50/3));
        body.getTransform().setRotation(2f);

        Fixture fixture = body.createFixture(Geometry.createCirle(5));
        fixture.setRestitution(2f);
        fixture.setFriction(0.6f);
        fixture.setDensity(0.1f);

        Body floorBody = world.getPhysicsWorld().createBody();
        floorBody.setType(BodyType.STATIC);
        floorBody.getTransform().setTranslation(new Vec2(80+50/3, 400/3));
        floorBody.getTransform().setRotation(0.1f);
        floorBody.createFixture(Geometry.createRectangle(500/3, 10/3));

        Body topBody = world.getPhysicsWorld().createBody();
        topBody.setType(BodyType.STATIC);
        topBody.getTransform().setTranslation(80+50/3, 20/3);
        topBody.getTransform().setRotation(0.1f);
        topBody.createFixture(Geometry.createRectangle(500/3, 10/3));

        Settings.maxPolygonVertices = 50;

        world.addObject(new Level1());
        world.addObject(new Player(new Point2D.Double(40, 160)));

        engine.start();

        //body.applyLinearImpulse(new Vec2(0, 300f), body.getWorldCenter(), true);
    }
}