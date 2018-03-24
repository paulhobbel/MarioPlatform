package me.paulhobbel.engine.component;

import me.paulhobbel.engine.GameObject;

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

    public void pause() {
    }

    public void resume() {
    }

    public void stop() {
    }

    public void update() {
    }
}