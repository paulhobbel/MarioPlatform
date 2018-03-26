package me.paulhobbel.engine;

import me.paulhobbel.engine.graphics.Camera;
import me.paulhobbel.engine.map.tiled.TiledMap;
import me.paulhobbel.engine.physics.Collidable;

import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.PathIterator;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Collections;

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
                System.out.println(collidable1);
                return true;
            }
        }
        return false;
    }
}
