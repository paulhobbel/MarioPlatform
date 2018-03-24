package me.paulhobbel.engine.window;

import me.paulhobbel.engine.Engine;
import me.paulhobbel.engine.graphics.Camera;
import me.paulhobbel.engine.map.tiled.TiledMap;
import me.paulhobbel.engine.map.tiled.TiledMapLoader;
import me.paulhobbel.engine.map.tiled.renderers.OrthogonalTiledMapRenderer;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;

public class WindowPanel extends JPanel {

    WindowPanel(Dimension dimension) {
        setPreferredSize(dimension);
        setFocusable(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        Engine.getInstance().getWorld().draw(g2d);
    }
}
