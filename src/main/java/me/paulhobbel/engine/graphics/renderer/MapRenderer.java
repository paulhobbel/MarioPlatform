package me.paulhobbel.engine.graphics.renderer;

import me.paulhobbel.engine.component.MapComponent;
import me.paulhobbel.engine.graphics.Renderer;

import java.awt.*;

public class MapRenderer implements Renderer {

    private MapComponent map;

    private static MapRenderer instance;

    public static MapRenderer getInstance() {
        if(instance == null) instance = new MapRenderer();
        return instance;
    }

    public void setMap(MapComponent map) {
        this.map = map;
    }

    @Override
    public void render(Graphics2D g2d) {
        if(map != null) {
            map.getMap().getStrategy().render(g2d, map);
        }
    }
}
