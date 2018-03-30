package me.paulhobbel.engine.window;

import me.paulhobbel.engine.core.Engine;
import me.paulhobbel.engine.graphics.renderer.DebugDraw;
import me.paulhobbel.engine.graphics.renderer.MapRenderer;
import me.paulhobbel.engine.graphics.renderer.SpriteRenderer;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;

public class GamePanel extends JPanel {
    GamePanel(int width, int height) {
        Dimension s = new Dimension(width, height);
        setPreferredSize(s);
        setMaximumSize(s);
        setMinimumSize(s);

        setFocusable(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        g2d.setTransform(Engine.getInstance().getActiveWorld().getCamera().getTransform());

        //MapRenderer.getInstance().render(g2d);
        SpriteRenderer.getInstance().render(g2d);

        DebugDraw.getInstance().render(g2d);
    }
}
