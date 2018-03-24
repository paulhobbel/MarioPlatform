package me.paulhobbel.engine.map.tiled;

import me.paulhobbel.engine.map.MapRenderer;

import java.awt.*;

public interface TiledMapRenderer extends MapRenderer {
    void renderTileLayer(Graphics2D g2d, TiledMapTileLayer layer);
}
