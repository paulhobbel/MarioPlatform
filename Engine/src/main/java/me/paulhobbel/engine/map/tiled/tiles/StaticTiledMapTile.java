package me.paulhobbel.engine.map.tiled.tiles;

import me.paulhobbel.engine.map.tiled.TiledMapTile;

import java.awt.image.BufferedImage;

public class StaticTiledMapTile extends TiledMapTile {
    private BufferedImage image;

    public StaticTiledMapTile(BufferedImage image) {
        this.image = image;
    }

    @Override
    public void setImage(BufferedImage image) {
        this.image = image;
    }

    @Override
    public BufferedImage getImage() {
        return image;
    }
}
