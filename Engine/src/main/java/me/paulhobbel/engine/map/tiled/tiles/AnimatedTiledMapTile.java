package me.paulhobbel.engine.map.tiled.tiles;

import me.paulhobbel.engine.core.Updatable;
import me.paulhobbel.engine.map.tiled.TiledMapTile;

import java.awt.image.BufferedImage;
import java.util.Arrays;

public class AnimatedTiledMapTile extends TiledMapTile {

    private static double lastDeltaTime = 0;
    private static final long initialTimeOffset = System.currentTimeMillis();
    //private static final long initialTimeOffset = System.currentTimeMillis();

    private int totalDelay;
    private int[] tileDelays;
    private StaticTiledMapTile[] tileFrames;


    public AnimatedTiledMapTile(int[] tileDelays, StaticTiledMapTile[] tileFrames) {
        if(tileDelays.length != tileFrames.length) {
            throw new RuntimeException("Delay size is not the same as frame size");
        }

        this.tileDelays = tileDelays;
        this.tileFrames = tileFrames;

        totalDelay = 0;
        for(int delay : tileDelays) {
            totalDelay += delay;
        }
    }

    public int getCurrentFrameIndex () {
        int currentTime = (int)(lastDeltaTime % totalDelay);

        for (int i = 0; i < tileDelays.length; ++i) {
            int animationInterval = tileDelays[i];
            if (currentTime <= animationInterval) return i;
            currentTime -= animationInterval;
        }

        throw new RuntimeException("Could not determine current animation frame in AnimatedTiledMapTile. This should never happen!");
    }

    public TiledMapTile getCurrentFrame () {
        return tileFrames[getCurrentFrameIndex()];
    }

    public static void updateAnimationBaseTime() {
        lastDeltaTime = System.currentTimeMillis() - initialTimeOffset;
    }

    @Override
    public BufferedImage getImage() {
        return getCurrentFrame().getImage();
    }

    @Override
    public void setImage(BufferedImage image) {

    }
}
