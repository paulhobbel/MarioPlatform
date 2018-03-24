package me.paulhobbel.engine;

import me.paulhobbel.engine.graphics.Camera;
import me.paulhobbel.engine.map.Map;
import me.paulhobbel.engine.map.MapRenderer;
import me.paulhobbel.engine.map.tiled.TiledMap;
import me.paulhobbel.engine.map.tiled.renderers.OrthogonalTiledMapRenderer;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

public class World {
    private TiledMap map;
    private MapRenderer mapRenderer;
    private Camera camera;

    private ArrayList<GameObject> objects = new ArrayList<>();

    World() {
        camera = new Camera();
    }

    public void setMap(TiledMap map) {
        this.map = map;
        if(map.getProperties().get("orientation").equals("orthogonal")) {
            mapRenderer = new OrthogonalTiledMapRenderer(this.map);
        }
    }

    public void addObject(GameObject object, int priority) {
        objects.add(object);
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

    public void draw(Graphics2D g2d) {
        //AffineTransform old = g2d.getTransform();
        g2d.setTransform(camera.getTransform());
        if(mapRenderer != null) {
            mapRenderer.render(g2d);
        }
        //g2d.setTransform(old);

        for(GameObject object : objects) {
            object.draw(g2d);
        }
    }

    public TiledMap getMap() {
        return map;
    }
}
