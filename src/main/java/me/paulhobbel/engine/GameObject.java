package me.paulhobbel.engine;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Random;

public class GameObject {
    private Point2D position;
    private int frame = 0;
    private BufferedImage[] sprites;

    private static HashMap<String, BufferedImage[]> imageCache = new HashMap<>();

    public GameObject(Point2D position, String spriteFile, int rows, int columns) {
        this.position = position;

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

    /**
     * Update the game object
     * @param elapsedTime Elapsed time since last update
     */
    public void update(double elapsedTime) {
        frame = new Random().nextInt(sprites.length);
    }

    public void draw(Graphics2D g2d) {
        AffineTransform tx = new AffineTransform();
        tx.scale(3, 3);
        tx.translate(position.getX(), position.getY());
        tx.translate(0, -sprites[frame].getHeight());

        g2d.drawImage(sprites[frame], tx, null);
    }
}
