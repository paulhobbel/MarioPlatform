package me.paulhobbel.engine.graphics.renderer;

import me.paulhobbel.engine.graphics.Renderer;
import me.paulhobbel.engine.physics.collision.Collidable;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

public class CollisionRenderer implements Renderer {
    private static CollisionRenderer instance;

    public static CollisionRenderer getInstance() {
        if(instance == null) instance = new CollisionRenderer();
        return instance;
    }

    private ArrayList<Collidable> collidables = new ArrayList<>();

    private CollisionRenderer() {
    }

    public void addCollidable(Collidable collidable) {
        collidables.add(collidable);
    }

    @Override
    public void render(Graphics2D g2d) {
        for(Collidable collidable : collidables) {
            AffineTransform tx = new AffineTransform();
            tx.scale(3, 3);
            g2d.setColor(Color.BLUE);
            g2d.draw(tx.createTransformedShape(collidable.getBounds()));
        }
    }
}
