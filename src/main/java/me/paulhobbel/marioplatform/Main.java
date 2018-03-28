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
        Engine engine = Engine.getInstance();
        GameWorld world = engine.getWorld();

        world.addObject(new Level1());
        world.addObject(new Player(new Point2D.Double(250/Engine.PPM, 576 * 3 /Engine.PPM)));

        WindowManager.getInstance().createWindow(new Dimension(816, 672), "Test");

        engine.start();
    }
}