package me.paulhobbel.engine;

import me.paulhobbel.engine.map.Map;

import java.awt.*;
import java.util.ArrayList;

public class World {
    private Map map;
    private ArrayList<GameObject> objects = new ArrayList<>();

    World() {
        map = new Map();
    }

    private void setMap(Map map) {
        this.map = map;
    }

    public void addObject(GameObject object, int priority) {
        objects.add(object);
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
        map.draw(g2d);

        for(GameObject object : objects) {
            object.draw(g2d);
        }
    }

}
