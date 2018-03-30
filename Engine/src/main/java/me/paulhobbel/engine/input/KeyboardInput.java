package me.paulhobbel.engine.input;

import me.paulhobbel.engine.window.GamePanel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Arrays;

class KeyboardInput implements KeyListener {

    boolean[] keys = new boolean[256];
    boolean[] lastKeys = new boolean[256];

    KeyboardInput(GamePanel panel) {
        panel.addKeyListener(this);
    }

    void update() {
        lastKeys = Arrays.copyOf(keys, keys.length);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        keys[e.getKeyCode()] = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        keys[e.getKeyCode()] = false;
    }
}
