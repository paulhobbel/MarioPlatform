package me.paulhobbel.engine.physics.box2d;

import org.jbox2d.collision.shapes.Shape;
import org.jbox2d.collision.shapes.ShapeType;
import org.jbox2d.dynamics.Filter;

public class Fixture {
    org.jbox2d.dynamics.Fixture fixture;
    private Body body;
    protected Shape shape;

    protected Fixture(Body body, org.jbox2d.dynamics.Fixture fixture) {
        this.body = body;
        this.fixture = fixture;
    }

    protected void reset(Body body, org.jbox2d.dynamics.Fixture fixture) {
        this.body = body;
        this.fixture = fixture;
        this.shape = null;
    }

    public ShapeType getType() {
        return fixture.getType();
    }

    public Shape getShape() {
        return shape;
    }

    public void setSensor(boolean flag) {
        fixture.setSensor(flag);
    }

    public boolean isSensor() {
        return fixture.isSensor();
    }

    public void setFilterData(Filter filter) {
        fixture.setFilterData(filter);
    }

    public Filter getFilterData() {
        return fixture.getFilterData();
    }

    public void setDensity(float density) {
        fixture.setDensity(density);
        fixture.m_body.resetMassData();
    }

    public float getDensity() {
        return fixture.getDensity();
    }

    public void setRestitution(float restitution) {
        fixture.setRestitution(restitution);
    }

    public float getRestitution() {
        return fixture.getRestitution();
    }

    public void setFriction(float friction) {
        fixture.setFriction(friction);
    }

    public float getFriction() {
        return fixture.getFriction();
    }

    public void refilter() {
        fixture.refilter();
    }

    public Body getBody() {
        return body;
    }
}
