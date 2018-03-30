package me.paulhobbel.engine.map.tiled;

import me.paulhobbel.engine.map.MapProperties;

import java.util.Iterator;
import java.util.TreeMap;

public class TiledMapTileSet implements Iterable<TiledMapTile> {
    private String name;
    private MapProperties properties;
    private TreeMap<Integer, TiledMapTile> tiles = new TreeMap<>();

    public TiledMapTileSet() {
        properties = new MapProperties();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MapProperties getProperties() {
        return properties;
    }

    public TiledMapTile getTile(int id) {
        return tiles.get(id);
    }

    public void putTile(int id, TiledMapTile tile) {
        tiles.put(id, tile);
    }

    public void removeTile(int id) {
        tiles.remove(id);
    }

    @Override
    public Iterator<TiledMapTile> iterator() {
        return tiles.values().iterator();
    }

    public int size() {
        return tiles.size();
    }
}
