package me.paulhobbel.marioplatform.entities;

import me.paulhobbel.engine.core.Engine;
import me.paulhobbel.engine.graphics.Animation.PlayMode;
import me.paulhobbel.engine.input.Input;
import me.paulhobbel.engine.physics.box2d.BodyDef;
import me.paulhobbel.engine.physics.box2d.Geometry;
import me.paulhobbel.marioplatform.entities.Player.State;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.BodyType;

import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;

public class Player extends Entity<State> {
    public enum State {
        IDLE, WALKING, JUMPING
    }

    public Player(Vec2 position) {
        super(position, 0, "/sprites/mario.png", 12, 1);

        addAnimation(State.IDLE, 0, new int[] { 0 }, PlayMode.NORMAL);
        addAnimation(State.WALKING, 0.1f, new int[] { 1, 2, 3 }, PlayMode.LOOP);
        addAnimation(State.JUMPING, 0, new int[] { 5 }, PlayMode.NORMAL);

        setAnimation(State.IDLE);
        setScale(3);
    }

    @Override
    protected void defineBody(Vec2 position) {
        BodyDef def = new BodyDef();
        def.type = BodyType.DYNAMIC;
        def.position.set((position.x + 8) * 3f / Engine.PPM, (position.y + 8) * 3f / Engine.PPM);
        body = world.getPhysicsWorld().createBody(def);

        body.createFixture(Geometry.createRectangle(16 * 3 / Engine.PPM, 16 * 3 / Engine.PPM));
    }

    @Override
    public void update(double elapsedTime) {
        super.update(elapsedTime);

        Input input = Input.getInstance();
        if (input.isKeyPressed(KeyEvent.VK_D) && body.getLinearVelocity().x <= 4f) {
            body.applyForce(new Vec2(4f,0), body.getWorldCenter());
        } else if (input.isKeyPressed(KeyEvent.VK_A) && body.getLinearVelocity().x >= -4f) {
            body.applyForce(new Vec2(-4f,0), body.getWorldCenter());
        }

        if(input.isKeyDown(KeyEvent.VK_SPACE) && Math.abs(body.getLinearVelocity().y) == 0) {
            body.applyLinearImpulse(new Vec2(0, -7f), body.getWorldCenter(), true);
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
        tx.translate(0, -8);
        if(body.getLinearVelocity().x < 0) {
            tx.translate(getImage().getWidth(), 0);
            tx.scale(-1, 1);
        }

        return tx;
    }
}
