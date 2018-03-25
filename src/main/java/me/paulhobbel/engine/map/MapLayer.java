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

    /**
     * @return the name of the layer.
     */
    public String getName() {
        return name;
    }

    /**
     * Set the name of the layer.
     * @param name The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the opacity of the layer.
     */
    public float getOpacity() {
        return opacity;
    }

    /**
     * Set the opacity of the layer.
     * @param opacity The opacity
     */
    public void setOpacity(float opacity) {
        this.opacity = opacity;
    }

    /**
     * @return whether this layer is visible.
     */
    public boolean isVisible() {
        return visible;
    }

    /**
     * Set the visibility of the layer.
     * @param visible The visibility
     */
    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    /**
     * @return the objects of the layer.
     */
    public ArrayList<MapObject> getObjects() {
        return objects;
    }

    /**
     * @return the properties of the layer.
     */
    public MapProperties getProperties() {
        return properties;
    }
}
