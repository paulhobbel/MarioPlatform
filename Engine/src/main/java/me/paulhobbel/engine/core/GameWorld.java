package me.paulhobbel.engine.core;

import me.paulhobbel.engine.graphics.Camera;
import me.paulhobbel.engine.graphics.renderer.DebugRenderer;
import me.paulhobbel.engine.physics.box2d.World;
import org.jbox2d.common.Vec2;

import java.util.ArrayList;

public class GameWorld {
    private Camera camera;

    private ArrayList<GameObject> objects = new ArrayList<>();

    private World physicsWorld;

    GameWorld() {
        camera = new Camera();
        physicsWorld = new World(new Vec2(0f, 9.8f), false);
        DebugRenderer.getInstance().setScale(Engine.PPM);
        DebugRenderer.getInstance().setWorld(physicsWorld);
    }

    public void addObject(GameObject object) {
        objects.add(object);
        object.resume();
    }

    public void removeObject(GameObject object) {
        objects.remove(object);
        object.pause();
    }

    public Camera getCamera() {
        return camera;
    }

    public World getPhysicsWorld() {
        return physicsWorld;
    }

    /**
     * Update all objects of this world
     *
     * @param elapsedTime Elapsed time since last update
     */
    public void update(double elapsedTime) {
        physicsWorld.step((float) elapsedTime, 6, 2);

        for (GameObject object : objects) {
            object.update(elapsedTime);
        }
    }
}
