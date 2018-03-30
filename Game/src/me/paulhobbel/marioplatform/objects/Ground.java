package me.paulhobbel.marioplatform.objects;

import java.awt.*;

public class Ground extends StaticObject {
    public Ground(Rectangle bounds) {
        super(bounds);
        fixture.setUserData(this);
    }
}
