package me.paulhobbel.marioplatform;

import me.paulhobbel.engine.GameObject;
import me.paulhobbel.engine.window.input.InputManager;

import java.awt.event.KeyEvent;
import java.awt.geom.Point2D;
import java.util.Random;

public class Player extends GameObject {
    public Player(Point2D position) {
        super(position, "/sprites/mario.png", 12, 1);
    }

    @Override
    public void update(double elapsedTime) {
        super.update(elapsedTime);

        //frame = new Random().nextInt(sprites.length);

        InputManager inputManager = InputManager.getInstance();
        if (inputManager.isKeyPressed(KeyEvent.VK_D)) {
            double newX = Math.min(150, speed.getX() + 5000 * elapsedTime);
            speed = new Point2D.Double(newX, speed.getY());
        } else if (inputManager.isKeyPressed(KeyEvent.VK_A)) {
            double newX = Math.max(-150, speed.getX() - 5000 * elapsedTime);
            speed = new Point2D.Double(newX, speed.getY());
        } else {
            double newX = speed.getX() * 0.9;
            speed = new Point2D.Double(newX, speed.getY());
        }

        double newX = position.getX() + speed.getX() * elapsedTime;
        position = new Point2D.Double(newX, position.getY());

        if(position.getX()*3 - world.getCamera().getPosition().getX() > 140) {
            world.getCamera().getPosition().setLocation(position.getX()*3 - 140, 0);
        }
    }
}
