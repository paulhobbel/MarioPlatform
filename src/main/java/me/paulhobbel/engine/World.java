package me.paulhobbel.engine;

import me.paulhobbel.engine.graphics.Camera;
import me.paulhobbel.engine.physics.collision.Collidable;
import me.paulhobbel.engine.physics.collision.Contact;
import me.paulhobbel.engine.physics.collision.Contact.ContactType;
import me.paulhobbel.engine.physics.collision.ContactListener;

import java.util.ArrayList;

public class World {
    private Camera camera;

    private GameObjects objects = new GameObjects();

    World() {
        camera = new Camera();
    }

    public void addObject(GameObject object) {
        objects.add(object);
        object.start();
        object.resume();
    }

    public Camera getCamera() {
        return camera;
    }

    /**
     * Update all objects of this world
     * @param elapsedTime Elapsed time since last update
     */
    public void update(double elapsedTime) {
        tick();
        for(GameObject object : objects) {
            object.update(elapsedTime);
        }
    }

    public boolean hasCollision(double x, double y) {
        ArrayList<Collidable> collidables = objects.getByType(Collidable.class);

        for(Collidable collidable1: collidables) {
            if(collidable1.getBounds().contains(x, y)) {
                return true;
            }
        }
        return false;
    }

    public boolean hasCollision(double x, double y, double width, double height) {
        ArrayList<Collidable> collidables = objects.getByType(Collidable.class);

        for(Collidable collidable1: collidables) {
            if(collidable1.getBounds().intersects(x, y, width, height)) {
                return true;
            }
        }
        return false;
    }

    public boolean hasCollision(Collidable collidable) {
        ArrayList<Collidable> collidables = objects.getByType(Collidable.class);
        collidables.sort(Collidable.compareDistance(collidable.getPosition()));

        for(Collidable collidable1: collidables) {
            if(collidable1.getBounds().intersects(collidable.getBounds().getBounds2D()) && !collidable1.equals(collidable)) {
//                if(ContactListener.class.isInstance(collidable)) {
//                    ((ContactListener) collidable).onCollision(new Contact(collidable1, ContactType.BOTTOM));
//                }
                return true;
            }
        }
        return false;
    }

    private void tick() {
        ArrayList<ContactListener> contactListeners = objects.getByType(ContactListener.class);
        ArrayList<Collidable> collidables = objects.getByType(Collidable.class);

        for(ContactListener contactListener : contactListeners) {
            for(Collidable collidable : collidables) {
                if(collidable.equals(contactListener)) continue;

                if(collidable.getBounds().intersects(contactListener.getBounds().getBounds2D())) {
                    contactListener.onCollision(new Contact(collidable, ContactType.BOTTOM));
                }
            }
        }
    }
}
