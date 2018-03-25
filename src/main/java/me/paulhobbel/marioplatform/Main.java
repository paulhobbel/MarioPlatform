package me.paulhobbel.marioplatform;

import me.paulhobbel.engine.Engine;
import me.paulhobbel.engine.GameWorld;
import me.paulhobbel.engine.window.WindowManager;
import me.paulhobbel.marioplatform.entities.Player;
import me.paulhobbel.marioplatform.maps.Level1;
import org.dyn4j.dynamics.Body;
import org.dyn4j.geometry.Geometry;
import org.dyn4j.geometry.MassType;
import org.dyn4j.geometry.Rectangle;

import java.awt.Dimension;
import java.awt.geom.Point2D;

public class Main {
    public static void main(String[] args) {
        new Main();
    }

    public Main() {
        Engine engine = Engine.getInstance();
        GameWorld world = engine.getWorld();

        world.addObject(new Level1());
        world.addObject(new Player(new Point2D.Double(0, 0)));

        Body body = new Body();
        body.setMass(MassType.INFINITE);
        Rectangle rect = Geometry.createRectangle(1104 * 3.0 / 100.0, 32 * 3.0 / 100.0);
        body.addFixture(rect);
        body.getTransform().setTranslation(0 + rect.getWidth() / 2, 208 * 3.0 / 100);
        world.getPhysicsWorld().addBody(body);

        WindowManager.getInstance().createWindow(new Dimension(816, 672), "Test");

        engine.start();
    }
}