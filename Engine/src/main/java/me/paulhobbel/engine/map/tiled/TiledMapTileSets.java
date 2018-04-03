package me.paulhobbel.engine.map.tiled;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class TiledMapTileSets implements Iterable<TiledMapTileSet> {
    private Map<String, TiledMapTileSet> tilesets = new HashMap<>();

    public TiledMapTileSet getTileSet(String name) {
        return tilesets.get(name);
    }

    public void addTileSet(TiledMapTileSet tileset) {
        tilesets.put(tileset.getName(), tileset);
    }

    public void removeTileSet(String name) {
        tilesets.remove(name);
    }

    public TiledMapTile getTile(int id) {
        for(TiledMapTileSet tileSet : tilesets.values()) {
            TiledMapTile tile = tileSet.getTile(id);
            if(tile != null) {
                return tile;
            }
        }

        // Could not find that tile
        return null;
    }

    @Override
    public Iterator<TiledMapTileSet> iterator() {
        return tilesets.values().iterator();
    }
}
