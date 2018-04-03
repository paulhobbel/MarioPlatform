package me.paulhobbel.engine.window;

import me.paulhobbel.engine.core.Engine;
import me.paulhobbel.engine.graphics.renderer.DebugRenderer;
import me.paulhobbel.engine.graphics.renderer.HudRenderer;
import me.paulhobbel.engine.graphics.renderer.MapRenderer;
import me.paulhobbel.engine.graphics.renderer.SpriteRenderer;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;

public class GamePanel extends JPanel {

    private int width;
    private int height;

    private JCheckBox displayMap = new JCheckBox("Display Map", true);
    private JCheckBox displaySprites = new JCheckBox("Display Sprites", true);
    private JCheckBox displayDebug = new JCheckBox("Display Debug");

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

        displayMap.setFocusable(false);
        displayMap.setBackground(new Color(0, 0, 0, 0));
        displaySprites.setFocusable(false);
        displaySprites.setBackground(new Color(0, 0, 0, 0));
        displayDebug.setFocusable(false);
        displayDebug.setBackground(new Color(0, 0, 0, 0));

        add(displayMap);
        add(displaySprites);
        add(displayDebug);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        g2d.setClip(new Rectangle2D.Double(0, 0, width, height));

        AffineTransform originalTransform = g2d.getTransform();
        g2d.setTransform(Engine.getInstance().getActiveWorld().getCamera().getTransform());

        if(displayMap.isSelected())
            MapRenderer.getInstance().render(g2d);

        if(displaySprites.isSelected())
            SpriteRenderer.getInstance().render(g2d);

        if(displayDebug.isSelected())
            DebugRenderer.getInstance().render(g2d);

        g2d.setTransform(originalTransform);

        HudRenderer.getInstance().render(g2d);

        Toolkit.getDefaultToolkit().sync();
    }
}
