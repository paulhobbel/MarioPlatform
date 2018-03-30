package me.paulhobbel.engine.physics.box2d;

import org.jbox2d.callbacks.ContactImpulse;
import org.jbox2d.collision.Manifold;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.joints.Joint;
import org.jbox2d.dynamics.joints.JointDef;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class World {
    org.jbox2d.dynamics.World world;

    Map<org.jbox2d.dynamics.Body, Body> bodies = new HashMap<>();
    Map<org.jbox2d.dynamics.Fixture, Fixture> fixtures = new HashMap<>();

    public World(Vec2 gravity, boolean doSleep) {
        world = new org.jbox2d.dynamics.World(gravity);
        world.setAllowSleep(doSleep);
    }

    public void setContactFilter(final ContactFilter filter) {
        if(filter != null) {
            world.setContactFilter(new org.jbox2d.callbacks.ContactFilter() {
                @Override
                public boolean shouldCollide(org.jbox2d.dynamics.Fixture fixtureA, org.jbox2d.dynamics.Fixture fixtureB) {
                    return filter.shouldCollide(fixtures.get(fixtureA), fixtures.get(fixtureB));
                }
            });
        } else {
            world.setContactFilter(new org.jbox2d.callbacks.ContactFilter());
        }
    }

    public void setContactListener(final ContactListener listener) {
       Contact tmpContact = new Contact(this);
        if(listener != null) {
            world.setContactListener(new org.jbox2d.callbacks.ContactListener() {
                @Override
                public void beginContact(org.jbox2d.dynamics.contacts.Contact contact) {
                    tmpContact.contact = contact;
                    listener.beginContact(tmpContact);
                }

                @Override
                public void endContact(org.jbox2d.dynamics.contacts.Contact contact) {
                    tmpContact.contact = contact;
                    listener.endContact(tmpContact);
                }

                @Override
                public void preSolve(org.jbox2d.dynamics.contacts.Contact contact, Manifold oldManifold) {
                    tmpContact.contact = contact;
                    listener.preSolve(tmpContact, oldManifold);
                }

                @Override
                public void postSolve(org.jbox2d.dynamics.contacts.Contact contact, ContactImpulse impulse) {
                    tmpContact.contact = contact;
                    listener.postSolve(tmpContact, impulse);
                }
            });
        } else {
            world.setContactListener(null);
        }
    }

    public Body createBody() {
        return createBody(new BodyDef());
    }

    public Body createBody(BodyDef def) {
        org.jbox2d.dynamics.Body b = world.createBody(def);
        Body body = new Body(this, b);
        bodies.put(b, body);

        return body;
    }

    public void destroyBody(Body body) {
        world.destroyBody(body.body);
        bodies.remove(body.body);
        for(Fixture fixture : body.fixtures) {
            fixtures.remove(fixture.fixture);
        }
    }

    // TODO: Fix joints sometime
    public Joint createJoint(JointDef def) {
        return world.createJoint(def);
    }

    public void destroyJoint(Joint joint) {
        world.destroyJoint(joint);
    }

    public void step(float timeStep, int velocityIterations, int positionIterations) {
        world.step(timeStep, velocityIterations, positionIterations);
    }

    public void clearForces() {
        world.clearForces();
    }

    public void setWarmStarting(boolean flag) {
        world.setWarmStarting(flag);
    }

    public void setContinuousPhysics(boolean flag) {
        world.setContinuousPhysics(flag);
    }

    public int getProxyCount() {
        return world.getProxyCount();
    }

    public int getBodyCount() {
        return world.getBodyCount();
    }

    public int getJointCount() {
        return world.getJointCount();
    }

    public int getContactCount() {
        return world.getContactCount();
    }

    public void setGravity(Vec2 gravity) {
        world.setGravity(gravity);
    }

    public Vec2 getGravity() {
        return world.getGravity();
    }

    public boolean isLocked() {
        return world.isLocked();
    }

    public void setAutoClearForces(boolean flag) {
        world.setAutoClearForces(flag);
    }

    public boolean getAutoClearForces() {
        return world.getAutoClearForces();
    }

    public ArrayList<Contact> getContactList() {
        org.jbox2d.dynamics.contacts.Contact contactList = world.getContactList();
        ArrayList<Contact> contacts = new ArrayList<>();

        // TODO: Create some sort utility for this instead, this is not efficient at all...
        while (contactList != null) {
            Contact contact = new Contact(this, contactList);
            contacts.add(contact);
            contactList = contactList.m_next;
        }

        return contacts;
    }

    public ArrayList<Body> getBodyList() {
        return new ArrayList<>(bodies.values());
    }

    public ArrayList<Joint> getJointList() {
        Joint jointList = world.getJointList();
        ArrayList<Joint> joints = new ArrayList<>();

        // TODO: Create some sort utility for this instead, this is not efficient at all...
        while (jointList != null) {
            joints.add(jointList);
            jointList = jointList.m_next;
        }

        return joints;
    }
}
