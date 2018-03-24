package me.paulhobbel.marioplatform;

import me.paulhobbel.engine.Engine;
import me.paulhobbel.engine.GameObject;
import me.paulhobbel.engine.World;
import me.paulhobbel.engine.component.Map;
import me.paulhobbel.engine.window.WindowManager;
import me.paulhobbel.marioplatform.entities.Player;
import me.paulhobbel.marioplatform.maps.Level1;

import java.awt.*;
import java.awt.geom.Point2D;

public class Main {
    public static void main(String[] args) {
        new Main();
    }

    public Main() {
        WindowManager.getInstance().createWindow(new Dimension(1280, 672), "Test");

        Engine engine = Engine.getInstance();
        World world = engine.getWorld();

        world.addObject(new Level1());
        world.addObject(new Player(new Point2D.Double(40, 192)));
        engine.start();
    }
}