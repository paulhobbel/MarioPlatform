package me.paulhobbel.marioplatform.entities;

import me.paulhobbel.engine.Engine;
import me.paulhobbel.engine.GameObject;
import me.paulhobbel.engine.GameWorld;
import me.paulhobbel.engine.component.SpriteComponent;
import me.paulhobbel.engine.window.input.InputManager;
import org.dyn4j.dynamics.joint.MotorJoint;
import org.dyn4j.geometry.Geometry;
import org.dyn4j.geometry.MassType;
import org.dyn4j.geometry.Vector2;

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

        body.addFixture(Geometry.createRectangle(sprite.getImage().getWidth() * 3.0 / 100.0, sprite.getImage().getHeight() * 3.0 / 100.0));
        body.getFixture(0).setFriction(0.25);
        body.setMass(MassType.NORMAL);
        //body.get

        setScale(3);
    }

    @Override
    public void update(double elapsedTime) {
        InputManager inputManager = InputManager.getInstance();
        if (inputManager.isKeyPressed(KeyEvent.VK_D) && body.getLinearVelocity().x <= 3) {
            //double newX = Math.min(150, speed.getX() + 5000 * elapsedTime);
            //speed = new Point2D.Double(newX, speed.getY());
            //body.applyForce(new Vector2(10, 0));
            //body.getLinearVelocity().set(new Vector2(10, 0));
            //MotorJoint motor = new MotorJoint(body);
            body.applyImpulse(new Vector2(0.1f, 0));
        } else if (inputManager.isKeyPressed(KeyEvent.VK_A) && body.getLinearVelocity().x >= -3) {
            //double newX = Math.max(-150, speed.getX() - 5000 * elapsedTime);
            //speed = new Point2D.Double(newX, speed.getY());
            //body.applyForce(new Vector2(-10, 0));
            //body.getLinearVelocity().set(new Vector2(-10, 0));
            body.applyImpulse(new Vector2(-0.1f, 0));
        } else {
            double newX = speed.getX() * 0.9;
            speed = new Point2D.Double(newX, speed.getY());
        }

        if(inputManager.isKeyPressed(KeyEvent.VK_SPACE)) {
            body.applyImpulse(new Vector2(0, -1f));
            //speed = new Point2D.Double(speed.getX(), -150);
        }

        double newX = speed.getX() * elapsedTime;
        //body.translate(newX / 45, 0);

        if(Math.abs(Math.round(newX)) > 0) {
            sprite.setFrame((int) ((System.currentTimeMillis() / 100) % 4));
        } else {
            sprite.setFrame(0);
        }

        GameWorld world = Engine.getInstance().getWorld();

        if(body.getTransform().getTranslationX() * 100 - world.getCamera().getPosition().getX() > 240) {
            world.getCamera().getPosition().setLocation(body.getTransform().getTranslationX() * 100 - 240, 0);
        }
    }

    @Override
    public AffineTransform getTransform() {
        AffineTransform tx = super.getTransform();
        if(speed.getX() < 0) {
            //tx.translate(sprite.getImage().getWidth(), 0);
            tx.scale(-1, 1);
        }

        return tx;
    }
}
