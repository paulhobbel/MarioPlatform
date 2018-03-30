package me.paulhobbel.engineOld.component;

import me.paulhobbel.engineOld.GameObject;

public class Component {
    private GameObject parent;

    public Component(GameObject parent) {
        this.parent = parent;
    }

    public GameObject getParent() {
        return parent;
    }

    public void start() {
    }

    public void resume() {
    }

    public void pause() {
    }

    public void stop() {
    }

    public void update(double elapsedTime) {
    }
}