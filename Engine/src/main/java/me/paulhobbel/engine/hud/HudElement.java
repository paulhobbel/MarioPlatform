package me.paulhobbel.engine.hud;

import java.awt.*;
import java.awt.geom.Point2D;

public abstract class HudElement {
    protected Point2D position;

    public HudElement(int x, int y) {
        this(new Point2D.Double(x, y));
    }

    public HudElement(Point2D position) {
        this.position = position;
    }

    public abstract void draw(Graphics2D g2d);
}
