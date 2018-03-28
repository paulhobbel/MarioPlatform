package me.paulhobbel.engine.objects;

import me.paulhobbel.engine.Engine;
import me.paulhobbel.engine.GameObject;
import me.paulhobbel.engine.graphics.renderer.SpriteRenderer;
import me.paulhobbel.engine.physics.box2d.Body;
import me.paulhobbel.engine.physics.box2d.BodyDef;
import me.paulhobbel.engine.physics.collisionOld.Collidable;

import javax.imageio.ImageIO;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;

public class Sprite extends GameObject {
    private int layer;
    private int frame = 0;
    protected BufferedImage[] sprites;

    protected Body body;

    private static HashMap<String, BufferedImage[]> imageCache = new HashMap<>();

    public Sprite(Point2D position, int layer, String spriteFile) {
        this(position, layer, spriteFile, 1, 1);
    }

    public Sprite(Point2D position, int layer, String spriteFile, int rows, int columns) {
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

        BodyDef def = new BodyDef();
        def.position.set(((float) position.getX() + (getImage().getWidth() / 2)) * 3f / Engine.PPM, ((float) position.getY() + getImage().getHeight() / 2 ) * 3f / Engine.PPM);

        body = world.getPhysicsWorld().createBody(def);
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

//    @Override
//    public void pause() {
//        SpriteRenderer.getInstance().removeSprite(this);
//    }

    public void setFrame(int frame) {
        this.frame = frame;
    }

    @Override
    public AffineTransform getTransform() {

        AffineTransform tx = new AffineTransform();
//        tx.translate(body.getTransform().getTranslationX() * Engine.PPM, body.getTransform().getTranslationY() * Engine.PPM);
//        tx.rotate(body.getTransform().getRotation());
//        tx.scale(getScale(), getScale());

        tx.translate(
                body.getTransform().getTranslationX() * Engine.PPM,
                body.getTransform().getTranslationY() * Engine.PPM
        );
        tx.rotate(body.getTransform().getRotation());
        tx.scale(getScale(), getScale());
        tx.translate(-getImage().getWidth() / 2, -getImage().getHeight() / 2);

//        AffineTransform tx = new AffineTransform();
//        tx.translate(body.getTransform().getTranslationX() * 3, body.getTransform().getTranslationY() * 3);
//        tx.rotate(body.getTransform().getRotation());
//        tx.scale(getScale(), getScale());
//        tx.translate(-getImage().getWidth()/2, -16*3/2);


        return tx;
    }
}
