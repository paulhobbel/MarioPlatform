package me.paulhobbel.engine.physics.box2d;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.joints.JointType;

// TODO: Fix joints some time
public class Joint {
    World world;
    org.jbox2d.dynamics.joints.Joint joint;

    private final Vec2 anchorA = new Vec2();
    private final Vec2 anchorB = new Vec2();
    private final Vec2 reactionForce = new Vec2();

    protected Joint(World world, org.jbox2d.dynamics.joints.Joint joint) {
        this.world = world;
        this.joint = joint;
    }

    public JointType getType() {
        // TODO: Create own JointTypes
        return joint.getType();
    }

    public Body getBodyA() {
        return world.bodies.get(joint.getBodyA());
    }

    public Body getBodyB() {
        return world.bodies.get(joint.getBodyB());
    }

    public Vec2 getAnchorA() {
        joint.getAnchorA(anchorA);
        return anchorA;
    }

    public Vec2 getAnchorB() {
        joint.getAnchorB(anchorB);
        return anchorB;
    }

    public boolean getCollideConnected() {
        return joint.getCollideConnected();
    }

    public Vec2 getReactionForce (float inv_dt) {
        joint.getReactionForce(inv_dt, reactionForce);
        return reactionForce;
    }

    public float getReactionTorque (float inv_dt) {
        return joint.getReactionTorque(inv_dt);
    }

    public void setUserData(Object data) {
        joint.setUserData(data);
    }

    public Object getUserData() {
        return joint.getUserData();
    }
}
