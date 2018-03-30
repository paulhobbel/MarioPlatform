package me.paulhobbel.engineOld.window;

import me.paulhobbel.engineOld.window.input.InputManager;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {

    private InputManager inputManager;

    Window(Dimension dimension, String title) {
        super(title);

        WindowPanel panel = new WindowPanel(dimension);
        inputManager = new InputManager(panel);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setContentPane(panel);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public InputManager getInputManager() {
        return inputManager;
    }

    public void update() {
        getContentPane().repaint();
    }
}
