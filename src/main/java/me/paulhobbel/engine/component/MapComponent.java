package me.paulhobbel.engine.component;

import me.paulhobbel.engine.GameObject;
import me.paulhobbel.engine.graphics.renderer.MapRenderer;
import me.paulhobbel.engine.map.tiled.TiledMap;
import me.paulhobbel.engine.map.tiled.TiledMapLoader;

public class MapComponent extends Component {

    TiledMap map;

    public MapComponent(String mapFile, GameObject parent) {
        super(parent);
        TiledMapLoader loader = new TiledMapLoader();
        map = loader.load(mapFile);
    }

    @Override
    public void resume() {
        MapRenderer.getInstance().setMap(this);
    }

    @Override
    public void pause() {
        super.pause();
    }

    public TiledMap getMap() {
        return map;
    }
}
