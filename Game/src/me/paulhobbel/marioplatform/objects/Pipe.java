package me.paulhobbel.marioplatform.objects;

import me.paulhobbel.marioplatform.MarioGame;

import java.awt.*;

public class Pipe extends StaticObject {
    public Pipe(Rectangle bounds) {
        super(bounds);
        setCategoryFilter(MarioGame.OBJECT_BIT);
    }
}
