package me.paulhobbel.marioplatform;

import me.paulhobbel.engine.core.Engine;

public class Main {
    public static void main(String[] args) {
        Engine engine = Engine.getInstance();
        engine.settings.width = 912;
        engine.settings.height = 672;
        engine.settings.scale = 4f;
        engine.settings.fps = 60;

        engine.setApplication(MarioGame.getInstance());
    }
}
