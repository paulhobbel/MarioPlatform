package me.paulhobbel.marioplatform.entities;

import me.paulhobbel.engine.Engine;
import me.paulhobbel.engine.GameObject;
import me.paulhobbel.engine.World;
import me.paulhobbel.engine.component.SpriteComponent;
import me.paulhobbel.engine.window.input.InputManager;

import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

public class Player extends GameObject {

    private Point2D speed = new Point2D.Double(0, 0);
    private SpriteComponent sprite;

    public Player(Point2D position) {
        super(position);
        sprite = new SpriteComponent(0, "/sprites/mario.png", 12, 1, this);

        addComponent(sprite);
        setScale(3);
    }

    @Override
    public void update(double elapsedTime) {
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

        double newX = speed.getX() * elapsedTime;
        translate(newX, 0);

        if(Math.abs(Math.round(newX)) > 0) {
            sprite.setFrame((int) ((System.currentTimeMillis() / 100) % 4));
        } else {
            sprite.setFrame(0);
        }

        World world = Engine.getInstance().getWorld();

        if(getPosition().getX()*3 - world.getCamera().getPosition().getX() > 240) {
            world.getCamera().getPosition().setLocation(getPosition().getX()*3 - 240, 0);
        }
    }

    @Override
    public AffineTransform getTransform() {
        AffineTransform tx = super.getTransform();
        if(speed.getX() < 0) {
            tx.translate(sprite.getImage().getWidth(), 0);
            tx.scale(-1, 1);
        }

        return tx;
    }
}
