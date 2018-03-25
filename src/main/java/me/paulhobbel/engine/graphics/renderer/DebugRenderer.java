package me.paulhobbel.engine.graphics.renderer;

import me.paulhobbel.engine.component.DebugComponent;
import me.paulhobbel.engine.graphics.Renderer;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

public class DebugRenderer implements Renderer {
    private static DebugRenderer instance;

    public static DebugRenderer getInstance() {
        if(instance == null) instance = new DebugRenderer();
        return instance;
    }

    protected DebugRenderer() {

    }

    private ArrayList<DebugComponent> components = new ArrayList<>();

    public void addComponent(DebugComponent component) {
        components.add(component);
    }

    public void removeComponent(DebugComponent component) {
        components.remove(component);
    }

    @Override
    public void render(Graphics2D g2d) {
        for(DebugComponent component : components) {
            double scale = component.getParent().getScale();
            AffineTransform tx = AffineTransform.getScaleInstance(scale, scale);
            g2d.setColor(component.getColor());
            g2d.draw(tx.createTransformedShape(component.getShape()));
        }
    }
}
