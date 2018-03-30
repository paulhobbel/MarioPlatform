package me.paulhobbel.engineOld;

import me.paulhobbel.engineOld.component.Component;

import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.util.ArrayList;

public class GameObject {

    protected Point2D position;
    private double scale;
    private double rotation;

    protected GameWorld world;

    private ArrayList<Component> components = new ArrayList<>();

    public GameObject() {
        this(new Point2D.Double(0, 0));
    }

    public GameObject(Point2D initialPosition) {
        position = initialPosition;
        scale = 1;
        rotation = 0;
        world = Engine.getInstance().getWorld();
    }

    public void addComponent(Component component) {
        components.add(component);
        component.start();
    }

    @SuppressWarnings("unchecked")
    public <T> T getComponent(Class<T> component){
        for(Component c : components){
            if(component.isInstance(c))
                return (T) c;
        }
        return null;
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

    public double getScale() {
        return scale;
    }

    public void start() {
        for(Component component : components) {
            component.start();
        }
        resume();
    }

    protected void resume(){
        for(Component component : components)
            component.resume();
    }

    /**
     * Update the game object
     * @param elapsedTime Elapsed time since last update
     */
    public void update(double elapsedTime){
        for(Component component : components){
            component.update(elapsedTime);
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
        tx.scale(scale, scale);
        tx.translate(position.getX(), position.getY());

        return tx;
    }
}