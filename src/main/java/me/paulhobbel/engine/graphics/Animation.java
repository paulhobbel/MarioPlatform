package me.paulhobbel.engine.graphics;

import java.util.ArrayList;

public class Animation<T> {
    public enum PlayMode {
        NORMAL,
        REVERSED,
        LOOP,
        LOOP_REVERSED
    }

    private T[] frames;
    private double duration;

    private PlayMode mode = PlayMode.NORMAL;

    @SuppressWarnings("unchecked")
    public Animation(double duration, ArrayList<T> frames) {
        this(duration, (T[]) frames.toArray());
    }

    public Animation(double duration, T ...frames) {
        this.duration = duration;
        this.frames = frames;
    }

    public T[] getFrames() {
        return frames;
    }

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }

    public PlayMode getMode() {
        return mode;
    }

    public void setMode(PlayMode mode) {
        this.mode = mode;
    }

    public double getAnimationDuration() {
        return frames.length * duration;
    }

    public boolean isAnimationFinished(double stateTime) {
        int index = (int)(stateTime / duration);
        return frames.length - 1 < index;
    }

    public T getFrame(double stateTime) {
        return frames[getFrameIndex(stateTime)];
    }

    public int getFrameIndex(double stateTime) {
        if(frames.length == 1) return 0;

        int index = (int) (stateTime / duration);

        switch (mode) {
            case NORMAL:
                index = Math.min(frames.length - 1, index);
                break;
            case LOOP:
                index = index % frames.length;
                break;
            case REVERSED:
                index = Math.max(frames.length - index - 1, 0);
                break;
            case LOOP_REVERSED:
                index = index % frames.length;
                index = frames.length - index - 1;
                break;
        }

        return index;
    }
}
