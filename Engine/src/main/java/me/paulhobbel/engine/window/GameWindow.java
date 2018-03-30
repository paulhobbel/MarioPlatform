package me.paulhobbel.engine.window;

import javax.swing.*;
import java.awt.*;

public class GameWindow extends JFrame {
    private GamePanel panel;

    GameWindow(int width, int height) {
        panel = new GamePanel(width, height);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setContentPane(panel);
        setResizable(false);
        pack();
        setLocationRelativeTo(null);
    }

    public GamePanel getPanel() {
        return panel;
    }

    public void update() {
        panel.repaint();
    }
}
