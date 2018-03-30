package me.paulhobbel.engine.physics.box2d;

import org.jbox2d.collision.WorldManifold;

public class Contact {
    final World world;
    org.jbox2d.dynamics.contacts.Contact contact;

    final WorldManifold worldManifold = new WorldManifold();

    Contact(World world) {
        this.world = world;
    }

    protected Contact(World world, org.jbox2d.dynamics.contacts.Contact contact) {
        this.world = world;
        this.contact = contact;
    }

    public WorldManifold getWorldManifold() {
        return worldManifold;
    }

    public boolean isTouching() {
        return contact.isTouching();
    }

    public void setEnabled(boolean flag) {
        contact.setEnabled(flag);
    }

    public boolean isEnabled() {
        return contact.isEnabled();
    }

    public Fixture getFixtureA() {
        return world.fixtures.get(contact.m_fixtureA);
    }

    public Fixture getFixtureB() {
        return world.fixtures.get(contact.m_fixtureB);
    }

    public int getChildIndexA() {
        return contact.getChildIndexA();
    }

    public int getChildIndexB() {
        return contact.getChildIndexB();
    }

    public void setFriction(float friction) {
        contact.setFriction(friction);
    }

    public float getFriction() {
        return contact.getFriction();
    }

    public void resetFriction() {
        contact.resetFriction();
    }

    public void setRestitution(float restitution) {
        contact.setRestitution(restitution);
    }

    public float getRestitution() {
        return contact.getRestitution();
    }

    public void resetRestitution() {
        contact.resetRestitution();
    }

    public void setTangentSpeed(float speed) {
        contact.setTangentSpeed(speed);
    }

    public float getTangentSpeed() {
        return contact.getTangentSpeed();
    }
}
