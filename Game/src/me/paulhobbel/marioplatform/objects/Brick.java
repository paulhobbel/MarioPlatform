package me.paulhobbel.marioplatform.objects;

import me.paulhobbel.engine.map.Map;
import me.paulhobbel.marioplatform.MarioGame;

import java.awt.*;

public class Brick extends InteractiveObject {
    public Brick(Map map, Rectangle bounds) {
        super(map, bounds);
        fixture.setUserData(this);
        setCategoryFilter(MarioGame.BRICK_BIT);
    }

    @Override
    public void onHeadHitEnter() {
        System.out.println("Collision: Brick");
        getCell().setTile(null);
    }

    @Override
    public void onHeadHitLeave() {
        setCategoryFilter(MarioGame.DESTROYED_BIT);
    }
}
