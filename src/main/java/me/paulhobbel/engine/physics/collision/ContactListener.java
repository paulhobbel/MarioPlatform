package me.paulhobbel.engine.physics.collision;

import java.awt.Shape;

public interface ContactListener<T extends Shape> extends Collidable<T> {
//    void onTopCollision(Contact contact);
//    void onBottomCollision(Contact contact);
//    void onLeftCollision(Contact contact);
//    void onRightCollision(Contact contact);
    void onCollision(Contact contact);
}
