package me.paulhobbel.engine.map.tiled.renderers;

import me.paulhobbel.engine.graphics.Camera;
import me.paulhobbel.engine.map.MapLayer;
import me.paulhobbel.engine.map.tiled.TiledMap;
import me.paulhobbel.engine.map.tiled.TiledMapRenderer;
import me.paulhobbel.engine.map.tiled.TiledMapTileLayer;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;

public class OrthogonalTiledMapRenderer implements TiledMapRenderer {
    private TiledMap map;

    private Rectangle2D viewBounds;

    public OrthogonalTiledMapRenderer(TiledMap map) {
        this.map = map;
        viewBounds = new Rectangle2D.Double(0, 0, 0, 0);
    }

    public TiledMap getMap() {
        return map;
    }

    public void setMap(TiledMap map) {
        this.map = map;
    }

    public Rectangle2D getViewBounds() {
        return viewBounds;
    }

//    @Override
//    public void setCamera(Camera camera) {
//        // TODO: Set viewBounds here
//
//        //float width = camera.getViewportWidth() * camera.getZoom();
//        //float height = camera.getViewportHeight() * camera.getZoom();
//        //viewBounds.setRect();
//    }

    @Override
    public void renderTileLayer(Graphics2D g2d, TiledMapTileLayer layer) {
        int height = layer.getHeight();
        int width = layer.getWidth();
        int tileWidth = layer.getTileWidth();
        int tileHeight = layer.getTileHeight();
        for(int y = 0; y < height; y++) {
            for(int x = 0; x < width; x++) {
                TiledMapTileLayer.Cell cell = layer.getCell(x, y);

                if(cell != null) {
                    AffineTransform tx = new AffineTransform();
                    tx.scale(3, 3);
                    tx.translate(x * tileWidth, y * tileHeight);

                    g2d.drawImage(cell.getTile().getImage(), tx, null);
                }
            }
        }
    }

    @Override
    public void render(Graphics2D g2d) {
        for(MapLayer layer : map.getLayers()) {
            if(!layer.isVisible()) continue;

            if(layer instanceof TiledMapTileLayer) {
                renderTileLayer(g2d, (TiledMapTileLayer) layer);
            } else {
                // TODO: Add support for other layers
                System.out.println("Unknown layer type");
                System.out.println(layer);
            }

        }
    }
}
