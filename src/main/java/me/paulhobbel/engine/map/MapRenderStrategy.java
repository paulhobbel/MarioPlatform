package me.paulhobbel.engine.map;

import me.paulhobbel.engine.component.Map;
import me.paulhobbel.engine.map.tiled.TiledMap;

import java.awt.*;

public abstract class MapRenderStrategy {
    public static final MapRenderStrategy NULL = new NullMapRenderStrategy();

    public abstract void render(Graphics2D g2d, Map map);

    private static class NullMapRenderStrategy extends MapRenderStrategy {

        @Override
        public void render(Graphics2D g2d, Map map) {

        }
    }
}
