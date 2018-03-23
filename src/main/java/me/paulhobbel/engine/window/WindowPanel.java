package me.paulhobbel.engine.window;

import me.paulhobbel.engine.Engine;

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

        Engine.getInstance().getWorld().draw(g2d);
    }
}
