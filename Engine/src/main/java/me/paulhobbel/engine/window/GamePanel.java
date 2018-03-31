package me.paulhobbel.engine.window;

import me.paulhobbel.engine.core.Engine;
import me.paulhobbel.engine.graphics.renderer.DebugRenderer;
import me.paulhobbel.engine.graphics.renderer.MapRenderer;
import me.paulhobbel.engine.graphics.renderer.SpriteRenderer;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;

public class GamePanel extends JPanel {

    private int width;
    private int height;

    GamePanel(int width, int height) {
        Dimension s = new Dimension(width, height);
        setPreferredSize(s);
        setMaximumSize(s);
        setMinimumSize(s);

        setFocusable(true);

        // Disable Swing auto repaint behavior
        setIgnoreRepaint(true);

        this.width = width;
        this.height = height;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        g2d.setClip(new Rectangle2D.Double(0, 0, width, height));
        g2d.setTransform(Engine.getInstance().getActiveWorld().getCamera().getTransform());

        MapRenderer.getInstance().render(g2d);
        SpriteRenderer.getInstance().render(g2d);

        //DebugRenderer.getInstance().render(g2d);

        Toolkit.getDefaultToolkit().sync();
    }
}
