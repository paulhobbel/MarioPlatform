package me.paulhobbel.engineOld.window;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class WindowManager {
    private static WindowManager instance;

    private Window window;

    public static WindowManager getInstance() {
        if(instance == null) instance = new WindowManager();
        return instance;
    }

    public void createWindow(Dimension dimension, String title) {
        window = new Window(dimension, title);
        window.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                window = null;
            }
        });
    }

    public Window getWindow() {
        return window;
    }

    public boolean isWindowActive() {
        return window != null;
    }
}
