package me.paulhobbel.engine.map.tiled.strategies;

import me.paulhobbel.engine.core.Engine;
import me.paulhobbel.engine.map.MapLayer;
import me.paulhobbel.engine.map.MapRenderStrategy;
import me.paulhobbel.engine.map.tiled.TiledMapTileLayer;
import me.paulhobbel.engine.map.tiled.tiles.AnimatedTiledMapTile;
import me.paulhobbel.engine.objects.Map;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class OrthogonalTiledMapRenderStrategy extends MapRenderStrategy {

    private int offset;

    public OrthogonalTiledMapRenderStrategy() {
        offset = Engine.getInstance().settings.width / (16 * 3) + 1;
    }

    private void renderTileLayer(Graphics2D g2d, Map map, TiledMapTileLayer layer) {
        int height = layer.getHeight();
        int width = layer.getWidth();
        int tileWidth = layer.getTileWidth();
        int tileHeight = layer.getTileHeight();

        double xTranslation = -g2d.getTransform().getTranslateX() / 3d;
        int xStart = (int) (xTranslation / 16);

        for(int x = xStart; x < xStart + 20; x++) {
            for(int y = 0; y < height; y++) {
                TiledMapTileLayer.Cell cell = layer.getCell(x, y);

                if(cell != null && cell.getTile() != null) {
                    AffineTransform tx = map.getTransform();
                    tx.translate(x * tileWidth, y * tileHeight);

                    g2d.drawImage(cell.getTile().getImage(), tx, null);
                }
            }
        }
    }

    @Override
    public void render(Graphics2D g2d, Map map) {
        AnimatedTiledMapTile.updateAnimationBaseTime();

        for(MapLayer layer : map.getMap().getLayers()) {
            if(!layer.isVisible()) continue;

            if(layer instanceof TiledMapTileLayer) {
                renderTileLayer(g2d, map, (TiledMapTileLayer) layer);
            }
        }
    }
}
