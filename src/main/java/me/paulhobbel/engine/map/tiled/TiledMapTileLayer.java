package me.paulhobbel.engine.map.tiled;

import me.paulhobbel.engine.map.MapLayer;

public class TiledMapTileLayer extends MapLayer {

    private int width;
    private int height;

    private int tileWidth;
    private int tileHeight;

    private Cell[][] cells;

    public TiledMapTileLayer(int width, int height, int tileWidth, int tileHeight) {
        super();
        this.width = width;
        this.height = height;
        this.tileWidth = tileWidth;
        this.tileHeight = tileHeight;
        cells = new Cell[width][height];
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getTileWidth() {
        return tileWidth;
    }

    public int getTileHeight() {
        return tileHeight;
    }

    public void setCell(int x, int y, Cell cell) {
        // Check x and y
        if(x < 0 || x >= width) return;
        if(y < 0 || y >= height) return;

        cells[x][y] = cell;
    }

    public Cell getCell(int x, int y) {
        // Check x and y
        if(x < 0 || x >= width) return null;
        if(y < 0 || y >= height) return null;

        return cells[x][y];
    }

    public static class Cell {
        private TiledMapTile tile;

        public void setTile(TiledMapTile tile) {
            this.tile = tile;
        }

        public TiledMapTile getTile() {
            return tile;
        }
    }
}
