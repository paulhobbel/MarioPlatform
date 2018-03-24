package me.paulhobbel.marioplatform;

import me.paulhobbel.engine.Engine;
import me.paulhobbel.engine.map.tiled.TiledMap;
import me.paulhobbel.engine.map.tiled.TiledMapLoader;
import me.paulhobbel.engine.window.WindowManager;

import java.awt.*;
import java.awt.geom.Point2D;

public class Main {
    public static void main(String[] args) {
        new Main();
    }

    public Main() {
        WindowManager.getInstance().createWindow(new Dimension(1280, 672), "Test");

        TiledMapLoader loader = new TiledMapLoader();
        TiledMap map = loader.load("/maps/level1.json");

        Engine engine = Engine.getInstance();
        engine.getWorld().setMap(map);
        engine.getWorld().addObject(new Player(new Point2D.Double(40, 40)), 0);
        engine.start();
    }
}
