package me.paulhobbel.engine.physics.box2d;

import org.jbox2d.collision.shapes.MassData;
import org.jbox2d.collision.shapes.Shape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.joints.JointEdge;

import java.util.ArrayList;

public class Body {
    final World world;
    public final org.jbox2d.dynamics.Body body;
    final ArrayList<Fixture> fixtures = new ArrayList<>();

    private Transform transform;
    private MassData massData = new MassData();

    protected Body(World world, org.jbox2d.dynamics.Body body) {
        this.world = world;
        this.body = body;
        this.transform = new Transform(body);
    }

    public void setType(BodyType type) {
        body.setType(type);
    }

    public BodyType getType() {
        return body.getType();
    }

    public Vec2 getPosition() {
        return body.getPosition();
    }

    public float getAngle() {
        return body.getAngle();
    }

    public Vec2 getWorldCenter() {
        return body.getWorldCenter();
    }

    public Vec2 getLocalCenter() {
        return body.getLocalCenter();
    }

    public Vec2 getWorldPoint(Vec2 localPoint) {
        return body.getWorldPoint(localPoint);
    }

    public Vec2 getWorldVector(Vec2 localVector) {
        return body.getWorldVector(localVector);
    }

    public Vec2 getLocalVector(Vec2 worldVector) {
        return body.getLocalVector(worldVector);
    }

    public Vec2 getLinearVelocityFromLocalPoint(Vec2 localPoint) {
        return body.getLinearVelocityFromLocalPoint(localPoint);
    }

    public void setLinearDamping(float linearDamping) {
        body.setLinearDamping(linearDamping);
    }

    public float getLinearDamping() {
        return body.getLinearDamping();
    }

    public void setAngularDamping(float angularDamping) {
        body.setAngularDamping(angularDamping);
    }

    public float getAngularDamping() {
        return body.getAngularDamping();
    }

    public void setLinearVelocity(Vec2 velocity) {
        body.setLinearVelocity(velocity);
    }

    public void setLinearVelocity(float velocityX, float velocityY) {
        body.setLinearVelocity(new Vec2(velocityX, velocityY));
    }

    public Vec2 getLinearVelocity() {
        return body.getLinearVelocity();
    }

    public void setAngularVelocity(float omega) {
        body.setAngularVelocity(omega);
    }

    public float getAngularVelocity() {
        return body.getAngularVelocity();
    }

    public float getMass() {
        return body.getMass();
    }

    public float getInertia() {
        return body.getInertia();
    }

    public void setTransform(Vec2 position, float angle) {
        body.setTransform(position, angle);
    }

    public void setTransform(float x, float y, float angle) {
        body.setTransform(new Vec2(x, y), angle);
    }

    public void setMassData(MassData data) {
        body.setMassData(data);
    }

    public void resetMassData() {
        body.resetMassData();
    }

    public MassData getMassData() {
        body.getMassData(massData);

        return massData;
    }

    public Transform getTransform() {
        return transform;
    }

    public void setBullet(boolean flag) {
        body.setBullet(flag);
    }

    public boolean isBullet() {
        return body.isBullet();
    }

    public void setSleepingAllowed(boolean flag) {
        body.setSleepingAllowed(flag);
    }

    public boolean isSleepingAllowed() {
        return body.isSleepingAllowed();
    }

    public void setActive(boolean flag) {
        body.setActive(flag);
    }

    public boolean isActive() {
        return body.isActive();
    }

    public void setAwake(boolean flag) {
        body.setAwake(flag);
    }

    public boolean isAwake() {
        return body.isAwake();
    }

    public void setFixedRotation(boolean flag) {
        body.setFixedRotation(flag);
    }

    public boolean isFixedRotation() {
        return body.isFixedRotation();
    }

    public ArrayList<Fixture> getFixtureList() {
        return fixtures;
    }

    public ArrayList<JointEdge> getJointList() {
        JointEdge jointEdge = body.getJointList();
        ArrayList<JointEdge> joints = new ArrayList<>();

        // FIXME: Currently this is the only way sorry :/
        while (jointEdge != null) {
            joints.add(jointEdge);
            jointEdge = jointEdge.next;
        }

        return joints;
    }

    public void setGravityScale(float scale) {
        body.setGravityScale(scale);
    }

    public float getGravityScale() {
        return body.getGravityScale();
    }

    public void setUserData(Object data) {
        body.setUserData(data);
    }

    public Object getUserData() {
        return body.getUserData();
    }

    public World getWorld() {
        return world;
    }

    public void applyForce(Vec2 force, Vec2 point) {
        body.applyForce(force, point);
    }

    public void applyForce(float forceX, float forceY, float pointX, float pointY) {
        body.applyForce(new Vec2(forceX, forceY), new Vec2(pointX, pointY));
    }

    public void applyForceToCenter(Vec2 force) {
        body.applyForceToCenter(force);
    }

    public void applyForceToCenter(float forceX, float forceY) {
        body.applyForceToCenter(new Vec2(forceX, forceY));
    }

    public void applyTorque(float torque) {
        body.applyTorque(torque);
    }

    public void applyLinearImpulse(Vec2 impulse, Vec2 point, boolean wake) {
        body.applyLinearImpulse(impulse, point, wake);
    }

    public void applyAngularImpulse(float impulse) {
        body.applyAngularImpulse(impulse);
    }

    public Fixture createFixture(FixtureDef def) {
        org.jbox2d.dynamics.Fixture f = body.createFixture(def);

        Fixture fixture = new Fixture(this, f);
        fixture.shape = def.shape;
        fixtures.add(fixture);
        world.fixtures.put(f, fixture);

        return fixture;
    }

    public Fixture createFixture(Shape shape) {
        FixtureDef def = new FixtureDef();
        def.shape = shape;

        return createFixture(def);
    }

    public Fixture createFixture(Shape shape, float density) {
        FixtureDef def = new FixtureDef();
        def.shape = shape;
        def.density = density;

        return createFixture(def);
    }

    public void destroyFixture(Fixture fixture) {
        body.destroyFixture(fixture.fixture);
        fixtures.remove(fixture);
        world.fixtures.remove(fixture.fixture);
    }


}
