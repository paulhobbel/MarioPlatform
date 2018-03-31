package me.paulhobbel.engine.objects;

import me.paulhobbel.engine.graphics.Animation;
import me.paulhobbel.engine.graphics.Animation.PlayMode;
import org.jbox2d.common.Vec2;

import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class AnimatedSprite<T> extends Sprite {

    private Map<T, Animation<BufferedImage>> animations = new HashMap<>();
    private T previousKey;
    private T currentKey;
    private Animation<BufferedImage> currentAnimation;
    private double stateTimer = 0;

    public AnimatedSprite(Vec2 position, int layer, String spriteFile, int rows, int columns) {
        super(position, layer, spriteFile, rows, columns);
    }

    public void setAnimation(T key) {
        if(previousKey != key) {
            stateTimer = 0;
        }
        previousKey = currentKey;
        currentKey = key;
        currentAnimation = animations.get(key);
    }

    public void addAnimation(T key, double duration, int startIndex, int endIndex, PlayMode mode) {
        if(startIndex < 0||
                startIndex > sprites.length - 2 ||
                endIndex > sprites.length -1 ||
                startIndex > endIndex ||
                endIndex < startIndex) {
            throw new RuntimeException("Animation indexes out of range");
        }
        addAnimation(key, new Animation<>(duration,
                Arrays.copyOfRange(sprites, startIndex, endIndex)), mode);
    }

    public void addAnimation(T key, double duration, int[] indexes, PlayMode mode) {
        BufferedImage[] selectedSprites = new BufferedImage[indexes.length];

        for(int i = 0; i < indexes.length; i++) {
            if(indexes[i] > sprites.length - 1) {
                throw new RuntimeException("Animation index out of range");
            }

            selectedSprites[i] = sprites[indexes[i]];
        }

        addAnimation(key, new Animation<>(duration, selectedSprites), mode);
    }

    private void addAnimation(T key, Animation<BufferedImage> frames, PlayMode mode) {
        frames.setMode(mode);
        animations.put(key, frames);
    }

    @Override
    public void update(double elapsedTime) {
        super.update(elapsedTime);

        //System.out.println(elapsedTime);
        // TODO: Reset the stateTimer when possible
        stateTimer+= elapsedTime;

        //System.out.println(stateTimer);
    }

    @Override
    public BufferedImage getImage() {
        if(currentAnimation == null) {
            return super.getImage();
        }

        return currentAnimation.getFrame(stateTimer);
    }

    public double getStateTimer() {
        return stateTimer;
    }
}

