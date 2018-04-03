package me.paulhobbel.engine.objects;

import me.paulhobbel.engine.core.GameObject;
import me.paulhobbel.engine.graphics.renderer.SpriteRenderer;
import org.jbox2d.common.Vec2;

import javax.imageio.ImageIO;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;

public class Sprite extends GameObject {
    private int layer;
    private int frame = 0;
    protected BufferedImage[] sprites;

    private static HashMap<String, BufferedImage[]> imageCache = new HashMap<>();

    public Sprite(Vec2 position, int layer, String spriteFile) {
        this(position, layer, spriteFile, 1, 1);
    }

    public Sprite(Vec2 position, int layer, String spriteFile, int rows, int columns) {
        super(position);

        this.layer = layer;

        try {
            if(!imageCache.containsKey(spriteFile)) {
                BufferedImage img = ImageIO.read(getClass().getResource(spriteFile));

                BufferedImage[] sprites = new BufferedImage[rows * columns];

                for(int row = 0; row < rows; row++) {
                    for(int column = 0; column < columns; column++) {
                        sprites[row+rows*column] = img.getSubimage(
                                row * (img.getWidth()/rows),column * (img.getHeight()/columns),
                                img.getWidth()/rows,img.getHeight()/columns);
                    }
                }

                imageCache.put(spriteFile, sprites);
            }

            sprites = imageCache.get(spriteFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Integer getLayer() {
        return layer;
    }

    public BufferedImage getImage() {
        return sprites[frame];
    }

    @Override
    public void resume() {
        SpriteRenderer.getInstance().addSprite(this);
    }

    //@Override
    public void pause() {
        SpriteRenderer.getInstance().removeSprite(this);
    }

    public void setFrame(int frame) {
        this.frame = frame;
    }

    @Override
    public AffineTransform getTransform() {
        AffineTransform tx = super.getTransform();
        tx.translate(-getImage().getWidth() / 2, -getImage().getHeight() / 2);
        return tx;
    }
}
