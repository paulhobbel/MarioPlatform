package me.paulhobbel.marioplatform;

import me.paulhobbel.engine.core.Application;
import me.paulhobbel.engine.core.Engine;
import me.paulhobbel.engine.core.GameWorld;
import me.paulhobbel.engine.hud.Hud;
import me.paulhobbel.engine.hud.HudManager;
import me.paulhobbel.engine.hud.Label;
import me.paulhobbel.marioplatform.collision.WorldContactListener;
import me.paulhobbel.marioplatform.maps.Level;
import me.paulhobbel.marioplatform.maps.Level1;
import me.paulhobbel.marioplatform.maps.Level2;

import java.awt.*;

public class MarioGame extends Application {

    public static final short DEFAULT_BIT = 1;
    public static final short MARIO_BIT = 2;
    public static final short BRICK_BIT = 4;
    public static final short COIN_BIT = 8;
    public static final short DESTROYED_BIT = 16;
    public static final short ENEMY_BIT = 32;
    public static final short OBJECT_BIT = 64;
    public static final short ENEMY_HEAD_BIT = 128;
    public static final short END_BIT = 256;

    private static MarioGame instance;

    public static MarioGame getInstance() {
        if(instance == null) instance = new MarioGame();
        return instance;
    }

    private Label scoreLabel;
    private Label worldLabel;
    private Label timeLabel;
    private Level currentLevel;

    private double timeCount = 0;
    private int worldTimer = 300;

    private int score = 0;

    private MarioGame() {}

    @Override
    public void init() {
        GameWorld world = Engine.getInstance().getActiveWorld();

        world.getPhysicsWorld().setContactListener(new WorldContactListener());

        setLevel(new Level1());

        Hud sceneHud = new Hud();

        sceneHud.addElement(new Label(50, 50, "MARIO", new Label.LabelStyle(new Font("arial", Font.PLAIN, 40), Color.WHITE)));
        sceneHud.addElement(scoreLabel = new Label(50, 90, String.format("%06d", score), new Label.LabelStyle(new Font("arial", Font.PLAIN, 40), Color.WHITE)));

        sceneHud.addElement(worldLabel = new Label(340, 50, "WORLD 1 - 1", new Label.LabelStyle(new Font("arial", Font.PLAIN, 40), Color.WHITE)));

        sceneHud.addElement(new Label(753, 50, "TIME", new Label.LabelStyle(new Font("arial", Font.PLAIN, 40), Color.WHITE)));
        sceneHud.addElement(timeLabel = new Label(770, 90, String.format("%03d", worldTimer), new Label.LabelStyle(new Font("arial", Font.PLAIN, 40), Color.WHITE)));

        HudManager.getInstance().addHud("Scene", sceneHud);
    }

    @Override
    public void update(double elapsedTime) {
        timeCount += elapsedTime;

        if(timeCount >= 1) {
            worldTimer--;
            timeLabel.setText(String.format("%03d", worldTimer));
            timeCount = 0;
        }

        scoreLabel.setText(String.format("%06d", score));

        if(worldTimer <= 0) {
            Engine.getInstance().stop();
        }
    }

    public void addScore(int value) {
        score += value;
    }

    public void setLevel(Level level) {
        currentLevel = level;
        GameWorld world = Engine.getInstance().getActiveWorld();
        reset();
        world.addObject(currentLevel);
    }

    public void reset() {
        score = 0;
        worldTimer = 300;
        timeCount = 0;
    }

    public Level getLevel() {
        return currentLevel;
    }
}