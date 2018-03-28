package me.paulhobbel.engine.physics.box2d;

import org.jbox2d.collision.broadphase.BroadPhase;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.Fixture;

import java.util.Arrays;

public class Transform {
    private Body body;
    private Vec2 orientation = new Vec2();

    public Transform(Body body) {
        this.body = body;
    }

    public void setTranslation(Vec2 position) {
        setTranslation(position.x, position.y);
    }

    public void setTranslation(float x, float y) {
        assert (!body.m_world.isLocked());
        if (body.m_world.isLocked()) {
            return;
        }

        body.m_xf.p.set(x, y);
        org.jbox2d.common.Transform.mulToOutUnsafe(body.m_xf, body.m_sweep.localCenter, body.m_sweep.c);
        body.m_sweep.c0.set(body.m_sweep.c);
        body.m_sweep.a0 = body.m_sweep.a;
    }

    public Vec2 getTranslation() {
        return body.m_xf.p;
    }

    public float getTranslationX() {
        return body.m_xf.p.x;
    }

    public float getTranslationY() {
        return body.m_xf.p.y;
    }

    public void setRotation(float angle) {
        assert (!body.m_world.isLocked());
        if (body.m_world.isLocked()) {
            return;
        }

        body.m_xf.q.set(angle);

        org.jbox2d.common.Transform.mulToOutUnsafe(body.m_xf, body.m_sweep.localCenter, body.m_sweep.c);
        body.m_sweep.a = angle;

        body.m_sweep.c0.set(body.m_sweep.c);
        body.m_sweep.a0 = body.m_sweep.a;
    }

    public float getRotation() {
        return body.m_xf.q.getAngle();
    }

    public Vec2 getOrientation() {
        return orientation.set(body.m_xf.q.getCos(), body.m_xf.q.getSin());
    }
}
