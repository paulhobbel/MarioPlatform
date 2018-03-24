package me.paulhobbel.engine.map.tiled;

import me.paulhobbel.engine.map.Map;

public class TiledMap extends Map {

    private TiledMapTileSets tilesets;

    public TiledMap() {
        tilesets = new TiledMapTileSets();
    }

    public TiledMapTileSets getTileSets() {
        return tilesets;
    }
}
