package me.paulhobbel.engine.map;

import me.paulhobbel.engine.map.tiled.strategies.OrthogonalTiledMapRenderStrategy;

import java.util.ArrayList;

public class Map {
    private MapRenderStrategy strategy = MapRenderStrategy.NULL;

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

    public MapRenderStrategy getStrategy() {
        return strategy;
    }

    public void setStrategy(MapRenderStrategy strategy) {
        this.strategy = strategy;
    }
}
