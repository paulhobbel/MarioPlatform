package me.paulhobbel.engine.window;

public class WindowManager {
    private static WindowManager instance;

    public static WindowManager getInstance() {
        if(instance == null) instance = new WindowManager();
        return instance;
    }

    private GameWindow window;

    public GameWindow createWindow(int width, int height) {
        return window = new GameWindow(width, height);
    }

    public GameWindow getWindow() {
        return window;
    }
}
