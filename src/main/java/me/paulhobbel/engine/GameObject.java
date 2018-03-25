package me.paulhobbel.engine;

import me.paulhobbel.engine.component.Component;
import org.dyn4j.dynamics.Body;
import org.dyn4j.geometry.MassType;
import org.dyn4j.geometry.Transform;

import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.util.ArrayList;

public class GameObject {

    private Point2D position;
    private double scale;
    private double rotation;

    protected Body body;

    private ArrayList<Component> components = new ArrayList<>();

    public GameObject() {
        this(new Point2D.Double(0, 0));
    }

    public GameObject(Point2D initialPosition) {
        position = initialPosition;
        scale = 1;
        rotation = 0;
        body = new Body();
        body.setMass(MassType.INFINITE);
        body.getTransform().setTranslation(initialPosition.getX() / 100.0, -(initialPosition.getY() / 100.0));
    }

    public void addComponent(Component component) {
        components.add(component);
        component.start();
    }

    public void removeComponent(Component component) {
        component.stop();
        components.remove(component);
    }

    public Point2D getPosition() {
        return position;
    }

    public void setScale(double scale) {
        this.scale = scale;
    }

    public Body getBody() {
        return body;
    }

    public double getScale() {
        return scale;
    }

    public void start() {
        for(Component component : components) {
            component.start();
        }
    }

    protected void resume(){
        for(Component component : components)
            component.resume();
    }

    protected void update(){
        for(Component component : components){
            component.update();
        }
    }

    public void stop() {
        for(Component component : components) {
            component.stop();
        }
    }

    public void translate(double dx, double dy) {
        position.setLocation(position.getX() + dx,  position.getY() + dy);
    }

    public AffineTransform getTransform() {
        AffineTransform tx = new AffineTransform();

        tx.translate(
                body.getTransform().getTranslationX() * 100,
                body.getTransform().getTranslationY() * 100
        );
        tx.rotate(body.getTransform().getRotation());
        tx.scale(scale, scale);
        //tx.translate(position.getX(), position.getY());

        return tx;
    }

    /**
     * Update the game object
     * @param elapsedTime Elapsed time since last update
     */
    public void update(double elapsedTime) {
        //frame = new Random().nextInt(sprites.length);
    }
}
