package me.paulhobbel.engine.map.tiled;

import java.util.ArrayList;
import java.util.Iterator;

public class TiledMapTileSets implements Iterable<TiledMapTileSet> {
    private ArrayList<TiledMapTileSet> tilesets = new ArrayList<>();

    public TiledMapTileSet getTileSet(int index) {
        return tilesets.get(index);
    }

    public void addTileSet(TiledMapTileSet tileset) {
        tilesets.add(tileset);
    }

    public void removeTileSet(int index) {
        tilesets.remove(index);
    }

    public void removeTileSet(TiledMapTileSet tileset) {
        tilesets.remove(tileset);
    }

    public TiledMapTile getTile(int id) {
        // Go backwards through the array
        for(int i = tilesets.size() - 1; i >= 0; i--) {
            TiledMapTileSet tileset = tilesets.get(i);
            TiledMapTile tile = tileset.getTile(id);
            if(tile != null) {
                return tile;
            }
        }

        // Could not find that tile
        return null;
    }

    @Override
    public Iterator<TiledMapTileSet> iterator() {
        return tilesets.iterator();
    }
}
