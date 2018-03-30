package me.paulhobbel.engine.input;

import me.paulhobbel.engine.window.GamePanel;

import java.awt.event.*;
import java.awt.geom.Point2D;
import java.util.Arrays;

class MouseInput implements MouseListener, MouseMotionListener, MouseWheelListener {
    boolean[] buttons = new boolean[10];
    boolean[] lastButtons = new boolean[10];

    Point2D mousePosition = new Point2D.Double(0, 0);
    int scroll = 0;

    MouseInput(GamePanel panel) {
        panel.addMouseListener(this);
        panel.addMouseMotionListener(this);
        panel.addMouseWheelListener(this);
    }

    void update() {
        scroll = 0;
        lastButtons = Arrays.copyOf(buttons, buttons.length);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        buttons[e.getButton()] = true;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        buttons[e.getButton()] = false;
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        mousePosition.setLocation(e.getX(), e.getY());
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mousePosition.setLocation(e.getX(), e.getY());
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        scroll = e.getWheelRotation();
    }
}
