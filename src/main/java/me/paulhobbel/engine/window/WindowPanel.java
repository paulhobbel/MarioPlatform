package me.paulhobbel.engine.window;

import me.paulhobbel.engine.Engine;
import me.paulhobbel.engine.graphics.renderer.*;
import me.paulhobbel.engine.map.objects.RectangleMapObject;
import me.paulhobbel.engine.physics.box2d.Box2DDebugRenderer;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class WindowPanel extends JPanel {

    WindowPanel(Dimension dimension) {
        setPreferredSize(dimension);
        setFocusable(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

//        g2d.setTransform(Engine.getInstance().getWorld().getCamera().getTransform());
//
//        MapRenderer.getInstance().render(g2d);
//        SpriteRenderer.getInstance().render(g2d);
//
//        CollisionRenderer.getInstance().render(g2d);
//        DebugRenderer.getInstance().render(g2d);
        //Box2DDebugRenderer.getInstance().render(g2d);
        DebugDraw.getInstance().render(g2d);
    }
}
