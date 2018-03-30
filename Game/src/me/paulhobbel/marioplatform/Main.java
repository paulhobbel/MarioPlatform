package me.paulhobbel.marioplatform;

import me.paulhobbel.engine.core.Engine;
import me.paulhobbel.engine.core.GameWorld;
import me.paulhobbel.marioplatform.maps.Level1;
import me.paulhobbel.marioplatform.entities.Player;
import org.jbox2d.common.Vec2;

import java.awt.geom.Point2D;

public class Main {
    public static void main(String[] args) {
        new Main();
    }

    public Main() {
        Engine engine = Engine.getInstance();
        engine.settings.width = 816;
        engine.settings.height = 672;
        engine.settings.scale = 4f;
        engine.settings.fps = 60;

        GameWorld world = engine.getActiveWorld();

        world.addObject(new Level1());
        world.addObject(new Player(new Vec2(250/Engine.PPM, 576 * 3 /Engine.PPM)));

        engine.start();
    }
}