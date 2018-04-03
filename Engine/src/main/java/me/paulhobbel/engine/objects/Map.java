package me.paulhobbel.engine.objects;

import me.paulhobbel.engine.core.GameObject;
import me.paulhobbel.engine.graphics.renderer.MapRenderer;
import me.paulhobbel.engine.map.MapLayer;
import me.paulhobbel.engine.map.tiled.*;
import me.paulhobbel.engine.physics.box2d.BodyDef;
import me.paulhobbel.engine.physics.box2d.Geometry;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.BodyType;

import java.awt.geom.AffineTransform;
import java.util.ArrayList;

public class Map extends GameObject {
    TiledMap map;

    public Map(String mapFile) {
        this(new Vec2(0, 0), mapFile);
    }

    public Map(Vec2 position, String mapFile) {
        super(position);
        TiledMapLoader loader = new TiledMapLoader();
        map = loader.load(mapFile);

        BodyDef def = new BodyDef();
        def.type = BodyType.STATIC;
        def.position.set(position);

        body = world.getPhysicsWorld().createBody(def);
    }

    @Override
    public void resume() {
        MapRenderer.getInstance().setMap(this);
    }

    @Override
    public void update(double elapsedTime) {
        super.update(elapsedTime);
    }

    @Override
    public void pause() {
        MapRenderer.getInstance().setMap(null);
    }

    public TiledMap getMap() {
        return map;
    }
}
