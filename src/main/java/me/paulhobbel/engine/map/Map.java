package me.paulhobbel.engine.map;

import java.util.ArrayList;

public class Map {
    private MapRenderStrategy strategy = MapRenderStrategy.NULL;

    private ArrayList<MapLayer> layers = new ArrayList<>();
    private MapProperties properties;

    protected Map() {
        properties = new MapProperties();
    }

    /**
     * @return the layers of the map.
     */
    public ArrayList<MapLayer> getLayers() {
        return layers;
    }

    /**
     * @return the properties of the map.
     */
    public MapProperties getProperties() {
        return properties;
    }

    /**
     * Set the render strategy of the map.
     * @param strategy RenderStrategy of this map
     */
    public void setStrategy(MapRenderStrategy strategy) {
        this.strategy = strategy;
    }

    /**
     * @return the render strategy of the map.
     */
    public MapRenderStrategy getStrategy() {
        return strategy;
    }
}
