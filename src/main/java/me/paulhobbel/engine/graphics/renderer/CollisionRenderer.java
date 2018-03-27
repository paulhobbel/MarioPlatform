package me.paulhobbel.engine.graphics.renderer;

import me.paulhobbel.engine.graphics.Renderer;
import me.paulhobbel.engine.physics.collisionOld.Collidable;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

public class CollisionRenderer implements Renderer {
    private static CollisionRenderer instance;

    public static CollisionRenderer getInstance() {
        if(instance == null) instance = new CollisionRenderer();
        return instance;
    }

    private ArrayList<Collidable> collidables = new ArrayList<>();
    private ArrayList<Shape> shapes = new ArrayList<>();

    private CollisionRenderer() {
    }

    public void addCollidable(Collidable collidable) {
        collidables.add(collidable);
    }

    public void addShape(Shape shape) {
        shapes.add(shape);
    }

    @Override
    public void render(Graphics2D g2d) {
        for(Collidable collidable : collidables) {
            AffineTransform tx = new AffineTransform();
            tx.scale(3, 3);
            g2d.setColor(Color.BLUE);
            g2d.draw(tx.createTransformedShape(collidable.getBounds()));
        }

        for(Shape shape : shapes) {
            AffineTransform tx = new AffineTransform();
            tx.scale(3, 3);
            g2d.setColor(Color.BLUE);
            g2d.draw(tx.createTransformedShape(shape));
        }
    }
}
