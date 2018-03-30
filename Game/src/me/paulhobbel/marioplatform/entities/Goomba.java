package me.paulhobbel.marioplatform.entities;

import me.paulhobbel.engine.core.Engine;
import me.paulhobbel.engine.graphics.Animation.PlayMode;
import me.paulhobbel.engine.physics.box2d.BodyDef;
import me.paulhobbel.marioplatform.entities.Goomba.State;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.BodyType;

import java.awt.geom.Point2D;

public class Goomba extends Entity<State> {
    public enum State {
        WALKING
    }

    public Goomba(Vec2 position) {
        super(position, 0, "/sprites/goomba.png", 3, 1);

        addAnimation(State.WALKING, 0.3f, 0, 2, PlayMode.LOOP);
        setAnimation(State.WALKING);
        setScale(3);
    }
}
