package me.paulhobbel.engine.map.tiled.strategies;

import me.paulhobbel.engine.component.MapComponent;
import me.paulhobbel.engine.map.MapLayer;
import me.paulhobbel.engine.map.MapObject;
import me.paulhobbel.engine.map.MapRenderStrategy;
import me.paulhobbel.engine.map.objects.EllipseMapObject;
import me.paulhobbel.engine.map.objects.PolygonMapObject;
import me.paulhobbel.engine.map.objects.RectangleMapObject;
import me.paulhobbel.engine.map.tiled.TiledMapTileLayer;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class OrthogonalTiledMapRenderStrategy extends MapRenderStrategy {

    private void renderTileLayer(Graphics2D g2d, MapComponent map, TiledMapTileLayer layer) {
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

//    private void renderObjectGroup(Graphics2D g2d, MapComponent map, MapLayer layer) {
//        AffineTransform tx = AffineTransform.getScaleInstance(3, 3);
//        g2d.setColor(Color.RED);
//        for(MapObject object : layer.getObjects()) {
//            if(object instanceof RectangleMapObject) {
//                g2d.draw(tx.createTransformedShape(((RectangleMapObject) object).getRectangle()));
//            } else if(object instanceof EllipseMapObject) {
//                g2d.draw(tx.createTransformedShape(((EllipseMapObject) object).getEllipse()));
//            } else if(object instanceof PolygonMapObject) {
//                g2d.draw(tx.createTransformedShape(((PolygonMapObject) object).getPolygon()));
//            }
//        }
//    }

    @Override
    public void render(Graphics2D g2d, MapComponent map) {
        for(MapLayer layer : map.getMap().getLayers()) {
            if(!layer.isVisible()) continue;

            if(layer instanceof TiledMapTileLayer) {
                renderTileLayer(g2d, map, (TiledMapTileLayer) layer);
            } else {
                // TODO: Add support for other layers
                //renderObjectGroup(g2d, map, layer);

            }

        }
    }
}
