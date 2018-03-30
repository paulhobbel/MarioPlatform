package me.paulhobbel.engine.core;

import me.paulhobbel.engine.physics.box2d.Body;
import org.jbox2d.common.Vec2;

import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

public class GameObject {
    private double scale;

    protected GameWorld world;
    protected Body body;

    public GameObject() {
        this(new Vec2(0, 0));
    }

    public GameObject(Vec2 initialPosition) {
        scale = 1;
        world = Engine.getInstance().getActiveWorld();
    }

    public void setScale(double scale) {
        this.scale = scale;
    }

    public double getScale() {
        return scale;
    }

    public void resume() {}

    /**
     * Update the game object
     * @param elapsedTime Elapsed time since last update
     */
    public void update(double elapsedTime){
    }

    public AffineTransform getTransform() {
        AffineTransform tx = new AffineTransform();

        //System.out.println(body.getTransform().getTranslationX());

        tx.translate(
                body.getTransform().getTranslationX() * Engine.PPM,
                body.getTransform().getTranslationY() * Engine.PPM
        );
        tx.rotate(body.getTransform().getRotation());
        tx.scale(scale, scale);

        return tx;
    }
}