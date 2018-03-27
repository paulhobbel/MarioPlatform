package me.paulhobbel.marioplatform.entities;

import me.paulhobbel.engine.objects.AnimatedSprite;
import me.paulhobbel.engine.physics.collisionOld.Collidable;

import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class Entity<T> extends AnimatedSprite<T> implements Collidable {
    public Entity(Point2D position, int layer, String spriteFile, int rows, int columns) {
        super(position, layer, spriteFile, rows, columns);
    }

//    //@Override
//    public void onCollision(Contact contact) {
//        if(this instanceof Player)
//            System.out.println(contact);
//    }

    @Override
    public Rectangle2D getBounds() {
        return new Rectangle2D.Double(position.getX(), position.getY(), 16, 16);
    }
}
