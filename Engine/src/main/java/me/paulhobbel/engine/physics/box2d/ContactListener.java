package me.paulhobbel.engine.physics.box2d;

import org.jbox2d.callbacks.ContactImpulse;
import org.jbox2d.collision.Manifold;

public interface ContactListener {
    void beginContact(Contact contact);

    void endContact(Contact contact);

    void preSolve(Contact contact, Manifold oldManifold);

    void postSolve(Contact contact, ContactImpulse impulse);
}
