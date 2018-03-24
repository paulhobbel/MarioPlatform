package me.paulhobbel.engine.window.input;

import me.paulhobbel.engine.window.WindowPanel;

import java.awt.event.*;

public class InputManager {
    private static InputManager instance;

    private WindowPanel panel;
    private boolean[] keys = new boolean[255];
    private boolean[] buttons = new boolean[4];

    public InputManager(WindowPanel windowPanel) {
        panel = windowPanel;
        panel.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                keys[e.getKeyCode()] = true;
            }

            @Override
            public void keyReleased(KeyEvent e) {
                keys[e.getKeyCode()] = false;
            }
        });
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                buttons[e.getButton()] = true;
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                buttons[e.getButton()] = false;
            }
        });
        instance = this;
    }

    public static InputManager getInstance() {
        return instance;
    }

    public void addKeyListener(KeyListener listener) {
        panel.addKeyListener(listener);
    }
    public void addMouseListener(MouseListener listener) {
        panel.addMouseListener(listener);
    }

    public boolean isKeyPressed(int keyCode) {
        return keys[keyCode];
    }

    public boolean isButtonPressed(int buttonCode) {
        return buttons[buttonCode];
    }
}
