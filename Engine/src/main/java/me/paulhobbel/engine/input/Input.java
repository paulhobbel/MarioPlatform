package me.paulhobbel.engine.input;

import me.paulhobbel.engine.window.GameWindow;
import me.paulhobbel.engine.window.WindowManager;

import java.awt.geom.Point2D;

public class Input {

    private static Input instance;

    /**
     * @return an instance of Input
     */
    public static Input getInstance() {
        if(instance == null) instance = new Input();
        return instance;
    }

    private KeyboardInput keyboard;
    private MouseInput mouse;

    private Input() {
        GameWindow window = WindowManager.getInstance().getWindow();

        keyboard = new KeyboardInput(window.getPanel());
        mouse = new MouseInput(window.getPanel());
    }

    public void update() {
        keyboard.update();
        mouse.update();
    }

    /**
     * Check whether a given key is currently pressed.
     * @param keyCode The key code, TIP: use the KeyEvent.VK_* values
     */
    public boolean isKeyPressed(int keyCode) {
        return keyboard.keys[keyCode];
    }

    /**
     * Check whether a given key just got released.
     * @param keyCode The key code, TIP: use the KeyEvent.VK_* values
     */
    public boolean isKeyUp(int keyCode) {
        return !keyboard.keys[keyCode] && keyboard.lastKeys[keyCode];
    }

    /**
     * Check whether a given key just got pressed.
     * @param keyCode The key code, TIP: use the KeyEvent.VK_* values
     */
    public boolean isKeyDown(int keyCode) {
        return keyboard.keys[keyCode] && !keyboard.lastKeys[keyCode];
    }

    /**
     * Check whether a given button is currently pressed.
     * @param button The button code, TIP: use the ButtonEvent.BUTTON* values
     */
    public boolean isButtonPressed(int button) {
        return mouse.buttons[button];
    }

    /**
     * Check whether a given button just got released.
     * @param button The button code, TIP: use the ButtonEvent.BUTTON* values
     */
    public boolean isButtonUp(int button) {
        return !mouse.buttons[button] && mouse.lastButtons[button];
    }

    /**
     * Check whether a given button just got pressed.
     * @param button The button code, TIP: use the ButtonEvent.BUTTON* values
     */
    public boolean isButtonDown(int button) {
        return mouse.buttons[button] && !mouse.lastButtons[button];
    }

    public Point2D getMousePosition() {
        return mouse.mousePosition;
    }

    public int getMouseScroll() {
        return mouse.scroll;
    }
}
