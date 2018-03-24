package me.paulhobbel.engine.map.tiled;

import java.awt.image.BufferedImage;

public class TiledMapTile {
    private int id;
    private BufferedImage image;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }
}
