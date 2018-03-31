package me.paulhobbel.marioplatform.objects;

import me.paulhobbel.engine.core.Engine;
import me.paulhobbel.engine.map.Map;
import me.paulhobbel.engine.map.tiled.TiledMapTileLayer;
import org.jbox2d.dynamics.Filter;

import java.awt.*;

public abstract class InteractiveObject extends StaticObject {

    protected Map map;

    InteractiveObject(Map map, Rectangle bounds) {
        super(bounds);
        this.map = map;
    }

    public abstract void onHeadHitEnter();
    public abstract void onHeadHitLeave();

    protected TiledMapTileLayer.Cell getCell() {
        TiledMapTileLayer layer = (TiledMapTileLayer) map.getLayers().get(1);
        return layer.getCell(
                (int) (body.getPosition().x / 3f * Engine.PPM / 16),
                (int) (body.getPosition().y / 3f * Engine.PPM / 16));
    }
}
