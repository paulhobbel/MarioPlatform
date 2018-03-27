package me.paulhobbel.engine.physics.box2d;

import org.jbox2d.common.Vec2;

import java.util.Arrays;

public class Transform {
    public static final int POS_X = 0;
    public static final int POS_Y = 1;
    public static final int COS = 2;
    public static final int SIN = 3;

    public float[] vals = new float[4];

    private Vec2 position = new Vec2();
    private Vec2 orientation = new Vec2();

    public Transform() {}

    public Transform(Vec2 position, float angle) {
        setPosition(position);
        setRotation(angle);
    }

    public Transform(Vec2 position, Vec2 orientation) {
        setPosition(position);
        setOrientation(orientation);
    }

    public Vec2 mul(Vec2 v) {
        //System.out.println(Arrays.toString(vals));
        float x = vals[POS_X] + vals[COS] * v.x + -vals[SIN] * v.y;
        float y = vals[POS_Y] + vals[SIN] * v.x + vals[COS] * v.y;

        v.x = x;
        v.y = y;
        return v;
    }

    public void setPosition(Vec2 position) {
        vals[POS_X] = position.x;
        vals[POS_Y] = position.y;
    }

    public Vec2 getPosition() {
        return position.set(vals[0], vals[1]);
    }

    public void setRotation(float angle) {
        float cos = (float)Math.cos(angle);
        float sin = (float)Math.sin(angle);

        vals[COS] = cos;
        vals[SIN] = sin;
    }

    public float getRotation() {
        return (float)Math.atan2(vals[SIN], vals[COS]);
    }

    public void setOrientation(Vec2 orientation) {
        vals[COS] = orientation.x;
        vals[SIN] = orientation.y;
    }

    public Vec2 getOrientation() {
        return orientation.set(vals[COS], vals[SIN]);
    }
}
