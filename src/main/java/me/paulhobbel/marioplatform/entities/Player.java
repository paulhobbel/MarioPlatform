package me.paulhobbel.marioplatform.entities;

import me.paulhobbel.engine.Engine;
import me.paulhobbel.engine.graphics.Animation.PlayMode;
import me.paulhobbel.engine.window.input.InputManager;
import me.paulhobbel.marioplatform.entities.Player.State;
import org.jbox2d.common.Vec2;

import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

public class Player extends Entity<State>  {
    public enum State {
        IDLE, WALKING, JUMPING
    }

    public Player(Point2D position) {
        super(position, 0, "/sprites/mario.png", 12, 1);

        addAnimation(State.IDLE, 0, new int[] { 0 }, PlayMode.NORMAL);
        addAnimation(State.WALKING, 0.1f, new int[] { 1, 2, 3 }, PlayMode.LOOP);
        addAnimation(State.JUMPING, 0, new int[] { 5 }, PlayMode.NORMAL);

        setAnimation(State.IDLE);
        setScale(3);
    }

    @Override
    public void update(double elapsedTime) {
        super.update(elapsedTime);

        InputManager inputManager = InputManager.getInstance();
        if (inputManager.isKeyPressed(KeyEvent.VK_D) && body.getLinearVelocity().x <= 3f) {
            body.applyForce(new Vec2(2f,0), body.getWorldCenter());
        } else if (inputManager.isKeyPressed(KeyEvent.VK_A) && body.getLinearVelocity().x >= -3f) {
            body.applyForce(new Vec2(-2f,0), body.getWorldCenter());
        }

        if(inputManager.isKeyPressed(KeyEvent.VK_SPACE) && Math.abs(body.getLinearVelocity().y) == 0) {
            body.applyLinearImpulse(new Vec2(0, -13f), body.getWorldCenter(), true);
        }

        if(Math.abs(body.getLinearVelocity().y) > 0) {
            setAnimation(State.JUMPING);
        } else if(Math.abs(body.getLinearVelocity().x) > 0) {
            setAnimation(State.WALKING);
        } else {
            setAnimation(State.IDLE);
        }

        if(body.getTransform().getTranslationX() * Engine.PPM - world.getCamera().getPosition().getX() > 240) {
            world.getCamera().getPosition().setLocation(body.getTransform().getTranslationX() * Engine.PPM - 240, 0);
        }
    }

    @Override
    public AffineTransform getTransform() {
        AffineTransform tx = super.getTransform();
        if(body.getLinearVelocity().x < 0) {
            tx.translate(getImage().getWidth(), 0);
            tx.scale(-1, 1);
        }

        return tx;
    }
}
