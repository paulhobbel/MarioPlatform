package me.paulhobbel.engine.window;

import me.paulhobbel.engine.Engine;
import me.paulhobbel.engine.graphics.renderer.MapRenderer;
import me.paulhobbel.engine.graphics.renderer.SpriteRenderer;

import javax.swing.*;
import java.awt.*;

public class WindowPanel extends JPanel {

    WindowPanel(Dimension dimension) {
        setPreferredSize(dimension);
        setFocusable(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        g2d.setTransform(Engine.getInstance().getWorld().getCamera().getTransform());

        MapRenderer.getInstance().render(g2d);
        SpriteRenderer.getInstance().render(g2d);
    }
}
