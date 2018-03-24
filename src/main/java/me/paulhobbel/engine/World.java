package me.paulhobbel.engine;

import me.paulhobbel.engine.graphics.Camera;
import me.paulhobbel.engine.map.tiled.TiledMap;

import java.util.ArrayList;

public class World {
    private TiledMap map;
    private Camera camera;

    private ArrayList<GameObject> objects = new ArrayList<>();

    World() {
        camera = new Camera();
    }

    public void setMap(TiledMap map) {
        this.map = map;
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

    public TiledMap getMap() {
        return map;
    }
}
