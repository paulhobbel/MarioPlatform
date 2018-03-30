package me.paulhobbel.engine.physics.box2d;

public interface ContactFilter {
    boolean shouldCollide(Fixture fixtureA, Fixture fixtureB);
}
