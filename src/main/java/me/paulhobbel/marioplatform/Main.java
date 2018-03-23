package me.paulhobbel.marioplatform;

import me.paulhobbel.engine.Engine;
import me.paulhobbel.engine.window.WindowManager;

import java.awt.*;
import java.awt.geom.Point2D;

public class Main {
    public static void main(String[] args) {
        new Main();
    }

    public Main() {
        WindowManager.getInstance().createWindow(new Dimension(1280, 720), "Test");


        Engine engine = Engine.getInstance();
        engine.getWorld().addObject(new Player(new Point2D.Double(40, 40)), 0);
        engine.start();
    }
}
