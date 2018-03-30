package me.paulhobbel.engine.graphics.renderer;

import me.paulhobbel.engine.graphics.Renderer;
import me.paulhobbel.engine.objects.Sprite;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class SpriteRenderer implements Renderer {
    private Map<Integer, ArrayList<Sprite>> layers = new TreeMap<>();

    private static SpriteRenderer instance;

    public static SpriteRenderer getInstance() {
        if(instance == null) instance = new SpriteRenderer();

        return instance;
    }

    public SpriteRenderer() { }

    public void addSprite(Sprite sprite) {
        if(!layers.containsKey(sprite.getLayer())) {
            layers.put(sprite.getLayer(), new ArrayList<>());
        }
        layers.get(sprite.getLayer()).add(sprite);
    }

    public void removeSprite(Sprite sprite) {
        if(layers.containsKey(sprite.getLayer())) {
            layers.get(sprite.getLayer()).remove(sprite);
        }
    }

    @Override
    public void render(Graphics2D g2d) {
        for(ArrayList<Sprite> layer : layers.values()) {
            for(Sprite sprite : layer) {
                AffineTransform tx = sprite.getTransform();
                g2d.drawImage(sprite.getImage(), tx, null);
            }
        }
    }
}
