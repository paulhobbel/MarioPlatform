package me.paulhobbel.marioplatform;

import me.paulhobbel.engine.core.Engine;
import me.paulhobbel.engine.core.GameWorld;
import me.paulhobbel.marioplatform.collision.WorldContactListener;
import me.paulhobbel.marioplatform.maps.Level1;
import me.paulhobbel.marioplatform.entities.Player;
import org.jbox2d.common.Vec2;

public class MarioGame {

    public static final short DEFAULT_BIT = 1;
    public static final short MARIO_BIT = 2;
    public static final short BRICK_BIT = 4;
    public static final short COIN_BIT = 8;
    public static final short DESTROYED_BIT = 16;
    public static final short ENEMY_BIT = 32;
    public static final short OBJECT_BIT = 64;
    public static final short ENEMY_HEAD_BIT = 128;

    public MarioGame() {
        Engine engine = Engine.getInstance();
        engine.settings.width = 912;
        engine.settings.height = 672;
        engine.settings.scale = 4f;
        engine.settings.fps = 120;

        GameWorld world = engine.getActiveWorld();

        world.getPhysicsWorld().setContactListener(new WorldContactListener());

        world.addObject(new Level1());
        world.addObject(new Player(new Vec2(250/Engine.PPM, 576 * 3 /Engine.PPM)));

        engine.start();
    }

    public static void main(String[] args) {
        new MarioGame();
    }
}