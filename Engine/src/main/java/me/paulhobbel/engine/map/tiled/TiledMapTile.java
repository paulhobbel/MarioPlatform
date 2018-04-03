package me.paulhobbel.engine.map.tiled;

import java.awt.image.BufferedImage;

public abstract class TiledMapTile {
    private int id;

    /**
     * Set the id of the tile.
     * @param id The id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the tile id.
     */
    public int getId() {
        return id;
    }

    /**
     * Set the image of the tile.
     * @param image The image
     */
    public abstract void setImage(BufferedImage image);

    /**
     * @return the tile image.
     */
    public abstract BufferedImage getImage();
}
