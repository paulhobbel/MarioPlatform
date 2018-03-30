package me.paulhobbel.engine.physics.box2d;

import org.jbox2d.dynamics.joints.JointType;

public class JointDef extends org.jbox2d.dynamics.joints.JointDef {
    public Body bodyA = null;
    public Body bodyB = null;

    public JointDef(JointType type) {
        super(type);
    }
}
