package me.paulhobbel.engine;

import me.paulhobbel.engine.graphics.Camera;
import me.paulhobbel.engine.map.tiled.TiledMap;
import org.dyn4j.dynamics.World;
import org.dyn4j.geometry.Vector2;

import java.util.ArrayList;

public class GameWorld {
    private Camera camera;

    private ArrayList<GameObject> objects = new ArrayList<>();
    private World physicsWorld;

    GameWorld() {
        camera = new Camera();
        physicsWorld = new World();
        physicsWorld.setGravity(new Vector2(0, 9.8));
        //physicsWorld.getSettings().setMaximumRotation(0);
        //physicsWorld.getSettings().setMaximumAngularCorrection(0);
    }

    public void addObject(GameObject object) {
        objects.add(object);
        physicsWorld.addBody(object.getBody());
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
        physicsWorld.update(elapsedTime);
        for(GameObject object : objects) {
            object.update(elapsedTime);
        }
    }

    public World getPhysicsWorld() {
        return physicsWorld;
    }
}
