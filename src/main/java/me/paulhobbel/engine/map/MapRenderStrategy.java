package me.paulhobbel.engine.map;

import me.paulhobbel.engine.objects.Map;

import java.awt.*;

public abstract class MapRenderStrategy {
    /**
     * A fallback strategy if no renderer is available
     */
    public static final MapRenderStrategy NULL = new NullMapRenderStrategy();

    /**
     * Render a map component onto the g2d canvas
     * @param g2d Graphics2D canvas
     * @param map MapComponent to draw
     */
    public abstract void render(Graphics2D g2d, Map map);

    private static class NullMapRenderStrategy extends MapRenderStrategy {

        @Override
        public void render(Graphics2D g2d, Map map) {

        }
    }
}
