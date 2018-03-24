package me.paulhobbel.engine.map;

import java.util.ArrayList;

public class MapLayer {
    private String name;
    private float opacity;
    private boolean visible;
    private MapProperties properties;
    private ArrayList<MapObject> objects = new ArrayList<>();

    public MapLayer() {
        name = "";
        opacity = 1.0f;
        visible = true;
        properties = new MapProperties();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getOpacity() {
        return opacity;
    }

    public void setOpacity(float opacity) {
        this.opacity = opacity;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public ArrayList<MapObject> getObjects() {
        return objects;
    }

    public MapProperties getProperties() {
        return properties;
    }
}
