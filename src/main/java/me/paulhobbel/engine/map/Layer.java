package me.paulhobbel.engine.map;

import java.awt.*;

public abstract class Layer {
    private String name;

    public Layer(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract void draw(Graphics2D g2d);
}
