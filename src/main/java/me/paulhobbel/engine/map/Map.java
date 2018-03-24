package me.paulhobbel.engine.map;

import java.util.ArrayList;

public class Map {
    private ArrayList<MapLayer> layers = new ArrayList<>();
    private MapProperties properties;

    public Map() {
        properties = new MapProperties();
    }

    public ArrayList<MapLayer> getLayers() {
        return layers;
    }

    public MapProperties getProperties() {
        return properties;
    }
}
