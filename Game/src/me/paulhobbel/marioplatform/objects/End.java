package me.paulhobbel.marioplatform.objects;

import me.paulhobbel.marioplatform.MarioGame;

import java.awt.*;

public class End extends StaticObject {
    public End(Rectangle bounds) {
        super(bounds);
        setCategoryFilter(MarioGame.END_BIT);
    }
}
