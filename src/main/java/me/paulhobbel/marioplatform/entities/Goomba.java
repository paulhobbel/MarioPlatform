package me.paulhobbel.marioplatform.entities;

import me.paulhobbel.engine.graphics.Animation.PlayMode;
import me.paulhobbel.engine.objects.AnimatedSprite;
import me.paulhobbel.marioplatform.entities.Goomba.State;

import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class Goomba extends AnimatedSprite<State> {
    public enum State {
        WALKING
    }

    public Goomba(Point2D position) {
        super(position, 0, "/sprites/goomba.png", 3, 1);

        addAnimation(State.WALKING, 0.3f, 0, 2, PlayMode.LOOP);
        setAnimation(State.WALKING);
        setScale(3);
    }
}
