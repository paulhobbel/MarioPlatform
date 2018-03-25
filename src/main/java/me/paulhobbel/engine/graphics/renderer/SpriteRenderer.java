package me.paulhobbel.engine.graphics.renderer;

import me.paulhobbel.engine.component.SpriteComponent;
import me.paulhobbel.engine.graphics.Renderer;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class SpriteRenderer implements Renderer {
    private Map<Integer, ArrayList<SpriteComponent>> layers = new TreeMap<>();

    private static SpriteRenderer instance;

    public static SpriteRenderer getInstance() {
        if(instance == null) instance = new SpriteRenderer();

        return instance;
    }

    public SpriteRenderer() { }

    public void addSprite(SpriteComponent sprite) {
        if(!layers.containsKey(sprite.getLayer())) {
            layers.put(sprite.getLayer(), new ArrayList<>());
        }
        layers.get(sprite.getLayer()).add(sprite);
    }

    public void removeSprite(SpriteComponent sprite) {
        if(layers.containsKey(sprite.getLayer())) {
            layers.get(sprite.getLayer()).remove(sprite);
        }
    }

    @Override
    public void render(Graphics2D g2d) {
        for(ArrayList<SpriteComponent> layer : layers.values()) {
            for(SpriteComponent sprite : layer) {
                AffineTransform tx = sprite.getParent().getTransform();
                //tx.translate(0, -sprite.getImage().getHeight());
                tx.translate(-sprite.getImage().getWidth()/2, -sprite.getImage().getHeight()/2);
                g2d.drawImage(sprite.getImage(), tx, null);
            }
        }
    }
}
