package me.paulhobbel.engine.map.tiled;

import me.paulhobbel.engine.map.MapLayer;

public class TiledMapTileLayer extends MapLayer {

    private int width;
    private int height;

    private int tileWidth;
    private int tileHeight;

    private Cell[][] cells;

    TiledMapTileLayer(int width, int height, int tileWidth, int tileHeight) {
        super();
        this.width = width;
        this.height = height;
        this.tileWidth = tileWidth;
        this.tileHeight = tileHeight;
        cells = new Cell[width][height];
    }

    /**
     * @return the width of the layer.
     */
    public int getWidth() {
        return width;
    }

    /**
     * @return the height of the layer.
     */
    public int getHeight() {
        return height;
    }

    /**
     * @return the tile width of the layer.
     */
    public int getTileWidth() {
        return tileWidth;
    }

    /**
     * @return the tile height of the layer.
     */
    public int getTileHeight() {
        return tileHeight;
    }

    /**
     * Set a cell at a given x and y
     * @param x The x
     * @param y The y
     * @param cell The cell to add
     */
    public void setCell(int x, int y, Cell cell) {
        // Check x and y
        if(x < 0 || x >= width) return;
        if(y < 0 || y >= height) return;

        cells[x][y] = cell;
    }

    /**
     * Get a cell at a given x and y
     * @param x The x
     * @param y The y
     * @return a cell of the layer at given x and y
     */
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
