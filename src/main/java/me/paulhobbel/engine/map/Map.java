package me.paulhobbel.engine.map;

import java.awt.*;
import java.util.HashMap;

public class Map {
    private HashMap<String, Layer> layers = new HashMap<>();

    public void draw(Graphics2D g2d) {
        for(Layer layer : layers.values()) {
            layer.draw(g2d);
        }
    }
}
