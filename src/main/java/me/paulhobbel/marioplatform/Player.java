package me.paulhobbel.marioplatform;

import me.paulhobbel.engine.GameObject;
import me.paulhobbel.engine.window.input.InputManager;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;

public class Player extends GameObject {
    public Player(Point2D position) {
        super(position, "/sprites/mario.png", 12, 1);
    }

    @Override
    public void update(double elapsedTime) {
        super.update(elapsedTime);

        if(InputManager.getInstance().isKeyPressed(KeyEvent.VK_A)) {
            System.out.println("A is pressed");
        }

        if(InputManager.getInstance().isButtonPressed(MouseEvent.BUTTON1)) {
            System.out.println("Button 1 is pressed");
        }
    }
}
