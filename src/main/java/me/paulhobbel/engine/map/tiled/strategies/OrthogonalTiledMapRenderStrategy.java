package me.paulhobbel.engine.map.tiled.strategies;

import me.paulhobbel.engine.component.Map;
import me.paulhobbel.engine.map.MapLayer;
import me.paulhobbel.engine.map.MapRenderStrategy;
import me.paulhobbel.engine.map.tiled.TiledMap;
import me.paulhobbel.engine.map.tiled.TiledMapTileLayer;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class OrthogonalTiledMapRenderStrategy extends MapRenderStrategy {

    private void renderTileLayer(Graphics2D g2d, Map map, TiledMapTileLayer layer) {
        int height = layer.getHeight();
        int width = layer.getWidth();
        int tileWidth = layer.getTileWidth();
        int tileHeight = layer.getTileHeight();
        for(int y = 0; y < height; y++) {
            for(int x = 0; x < width; x++) {
                TiledMapTileLayer.Cell cell = layer.getCell(x, y);

                if(cell != null) {
                    AffineTransform tx = map.getParent().getTransform();
                    tx.translate(x * tileWidth, y * tileHeight);

                    g2d.drawImage(cell.getTile().getImage(), tx, null);
                }
            }
        }
    }

    @Override
    public void render(Graphics2D g2d, Map map) {
        for(MapLayer layer : map.getMap().getLayers()) {
            if(!layer.isVisible()) continue;

            if(layer instanceof TiledMapTileLayer) {
                renderTileLayer(g2d, map, (TiledMapTileLayer) layer);
            } else {
                // TODO: Add support for other layers
                System.out.println("Unknown layer type");
                System.out.println(layer);
            }

        }
    }
}
