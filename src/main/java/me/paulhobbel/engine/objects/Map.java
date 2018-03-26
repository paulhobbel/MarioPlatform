package me.paulhobbel.engine.objects;

import me.paulhobbel.engine.GameObject;
import me.paulhobbel.engine.graphics.renderer.MapRenderer;
import me.paulhobbel.engine.map.tiled.TiledMap;
import me.paulhobbel.engine.map.tiled.TiledMapLoader;

import java.awt.geom.Point2D;

public class Map extends GameObject {
    TiledMap map;

    public Map(String mapFile) {
        this(new Point2D.Double(0, 0), mapFile);
    }

    public Map(Point2D position, String mapFile) {
        super(position);
        TiledMapLoader loader = new TiledMapLoader();
        map = loader.load(mapFile);
    }

    @Override
    public void resume() {
        MapRenderer.getInstance().setMap(this);
    }

//    @Override
//    public void pause() {
//        MapRenderer.getInstance().setMap(null);
//    }

    public TiledMap getMap() {
        return map;
    }
}
