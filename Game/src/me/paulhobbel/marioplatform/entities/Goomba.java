package me.paulhobbel.marioplatform.entities;

import me.paulhobbel.engine.core.Engine;
import me.paulhobbel.engine.graphics.Animation.PlayMode;
import me.paulhobbel.engine.physics.box2d.BodyDef;
import me.paulhobbel.engine.physics.box2d.FixtureDef;
import me.paulhobbel.engine.physics.box2d.Geometry;
import me.paulhobbel.marioplatform.MarioGame;
import me.paulhobbel.marioplatform.entities.Goomba.State;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.BodyType;

import java.awt.geom.AffineTransform;

public class Goomba extends Entity<State> {
    private boolean shouldDestroy = false;
    private boolean destroyed = false;
    private Vec2 velocity;

    public enum State {
        WALKING,
        SMASHED
    }

    public Goomba(Vec2 position) {
        super(position, 0, "/sprites/goomba.png", 3, 1);

        addAnimation(State.WALKING, 0.4f, 0, 2, PlayMode.LOOP);
        addAnimation(State.SMASHED, 0, new int[] { 2 }, PlayMode.NORMAL);
        setAnimation(State.WALKING);
        setScale(3);

        velocity = new Vec2(1, 0);
    }

    @Override
    protected void defineBody(Vec2 position) {
        BodyDef def = new BodyDef();
        def.type = BodyType.DYNAMIC;
        def.position.set((position.x + 3) * 3f / Engine.PPM, (position.y + 3) * 3f / Engine.PPM);
        body = world.getPhysicsWorld().createBody(def);

        FixtureDef fdef = new FixtureDef();
        fdef.filter.categoryBits = MarioGame.ENEMY_BIT;
        fdef.filter.maskBits =
                MarioGame.DEFAULT_BIT |
                MarioGame.BRICK_BIT |
                MarioGame.COIN_BIT |
                MarioGame.MARIO_BIT |
                MarioGame.ENEMY_BIT |
                MarioGame.OBJECT_BIT;

        fdef.shape = Geometry.createCircle(6 * 3 / Engine.PPM);
        body.createFixture(fdef).setUserData(this);

        Vec2[] vertices = new Vec2[4];
        vertices[0] = new Vec2(-5, -8).mul(3f / Engine.PPM);
        vertices[1] = new Vec2(5, -8).mul(3f / Engine.PPM);
        vertices[2] = new Vec2(-3, -3).mul(3f / Engine.PPM);
        vertices[3] = new Vec2(3, -3).mul(3f / Engine.PPM);

        fdef.shape = Geometry.createPolygon(vertices);
        fdef.restitution = 0.5f;
        fdef.filter.categoryBits = MarioGame.ENEMY_HEAD_BIT;
        body.createFixture(fdef).setUserData(this);
//        fdef.shape = Geometry.createEdge(new Vec2(-2 * 3f / Engine.PPM, -6 * 3 / Engine.PPM), new Vec2(2 * 3f / Engine.PPM, -6 * 3 / Engine.PPM));
//        fdef.isSensor = true;
//        body.createFixture(fdef).setUserData("head");
    }

    @Override
    public void update(double elapsedTime) {
        super.update(elapsedTime);

        if(shouldDestroy && !destroyed) {
            world.getPhysicsWorld().destroyBody(body);
            destroyed = true;
            setAnimation(State.SMASHED);
        }
        if(!destroyed) {
            body.setLinearVelocity(velocity.x, body.getLinearVelocity().y);
        }

        //body.set
    }

    @Override
    public AffineTransform getTransform() {
        AffineTransform tx = super.getTransform();
        tx.translate(0, -2);
        return tx;
    }

    public void hitOnHead() {
        //world.getPhysicsWorld().destroyBody(body);
        shouldDestroy = true;
    }

    public void reverseVelocity(boolean x, boolean y) {
        if(x) velocity.x = -velocity.x;
        if(y) velocity.y = -velocity.y;
    }
}
