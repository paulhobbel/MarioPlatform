package me.paulhobbel.engine;

import me.paulhobbel.engine.graphics.Camera;
import me.paulhobbel.engine.map.tiled.TiledMap;
import org.dyn4j.geometry.Vector2;

import java.util.ArrayList;

public class World {
    private TiledMap map;
    private Camera camera;

    private ArrayList<GameObject> objects = new ArrayList<>();
    private org.dyn4j.dynamics.World physicsWorld;

    World() {
        camera = new Camera();
        physicsWorld = new org.dyn4j.dynamics.World();
        physicsWorld.setGravity(new Vector2(0, -9.8));
    }

    public void setMap(TiledMap map) {
        this.map = map;
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

    public TiledMap getMap() {
        return map;
    }
}
