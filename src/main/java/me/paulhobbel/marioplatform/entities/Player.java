package me.paulhobbel.marioplatform.entities;

import me.paulhobbel.engine.Engine;
import me.paulhobbel.engine.GameWorld;
import me.paulhobbel.engine.graphics.Animation.PlayMode;
import me.paulhobbel.engine.objects.AnimatedSprite;
import me.paulhobbel.engine.physics.collisionOld.Contact;
import me.paulhobbel.engine.physics.collisionOld.ContactListener;
import me.paulhobbel.engine.window.input.InputManager;
import me.paulhobbel.marioplatform.entities.Player.AnimationTypes;

import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class Player extends AnimatedSprite<AnimationTypes> implements ContactListener {

    @Override
    public void onCollision(Contact contact) {
        System.out.println(contact);

        Rectangle2D contactBounds = contact.getCollidable().getBounds().getBounds2D();
        Rectangle2D bounds = getBounds();

        double dy = 0;

        System.out.println(contact.getCollidable().getBounds());
        System.out.println(getBounds());

        switch (contact.getType()) {
            case LEFT:
                translate(contactBounds.getX() - bounds.getX() - bounds.getWidth(), 0);
                break;
            case RIGHT:
                translate(contactBounds.getX() + contactBounds.getWidth() - bounds.getX(), 0);
                break;
            case TOP:
                translate(0, contactBounds.getY() - bounds.getY() - bounds.getHeight());
                break;
            case BOTTOM:
                translate(0, contactBounds.getY() + contactBounds.getHeight() - bounds.getY());
                break;

        }
    }

    public enum AnimationTypes {
        IDLE,
        WALKING,
        JUMPING
    }

    private Point2D speed = new Point2D.Double(0, 0);

    public Player(Point2D position) {
        super(position, 0, "/sprites/mario.png", 12, 1);

        addAnimation(AnimationTypes.IDLE, 0, new int[] { 0 }, PlayMode.NORMAL);
        addAnimation(AnimationTypes.WALKING, 0.2f, new int[] { 1, 2, 3 }, PlayMode.LOOP);
        addAnimation(AnimationTypes.JUMPING, 0, new int[] { 5 }, PlayMode.NORMAL);

        setAnimation(AnimationTypes.IDLE);
        setScale(3);
    }

    @Override
    public void update(double elapsedTime) {
        super.update(elapsedTime);
        InputManager inputManager = InputManager.getInstance();
        if (inputManager.isKeyPressed(KeyEvent.VK_D)) {
            double newX = Math.min(80, speed.getX() + 5000 * elapsedTime);
            speed = new Point2D.Double(newX, speed.getY());
            setAnimation(AnimationTypes.WALKING);
        } else if (inputManager.isKeyPressed(KeyEvent.VK_A)) {
            double newX = Math.max(-80, speed.getX() - 5000 * elapsedTime);
            speed = new Point2D.Double(newX, speed.getY());
        } else if(Math.abs(speed.getX()) >= 0) {
            double newX = speed.getX() * 0.9;
            speed = new Point2D.Double(newX, speed.getY());
        }

        if (inputManager.isKeyPressed(KeyEvent.VK_S)) {
            double newY = Math.min(80, speed.getY() + 5000 * elapsedTime);
            speed = new Point2D.Double(speed.getX(), newY);
            //setAnimation(AnimationTypes.WALKING);
        } else if (inputManager.isKeyPressed(KeyEvent.VK_W)) {
            double newY = Math.max(-80, speed.getY() - 5000 * elapsedTime);
            speed = new Point2D.Double(speed.getX(), newY);
        } else if(Math.abs(speed.getX()) >= 0) {
            double newY = speed.getY() * 0.9;
            speed = new Point2D.Double(speed.getX(), newY);
        }

//        if(inputManager.isKeyPressed(KeyEvent.VK_SPACE)) {
//            setAnimation(AnimationTypes.JUMPING);
//            speed = new Point2D.Double(speed.getX(), -150);
//        }


        GameWorld world = Engine.getInstance().getWorld();
        double newX = speed.getX() * elapsedTime;
        double newY = speed.getY() * elapsedTime;
        boolean collision = false;

        //if(speed.getX() > 0) {
            //System.out.println(position);
            if (world.hasCollision(this)) {
                collision = true;
                //System.out.println("We have collisionOld!");
            }
        //}
//        else if(speed.getX() < 0) {
//            if (world.hasCollision(position.getX() + newX + 1, position.getY()+1))
//                collisionOld = true;
//        }d


        //Point2D oldPosition = new Point2D.Double(position.getX(), position.getY());
        translate(newX, newY);

//        if(!collisionOld) {
//            speed = new Point2D.Double(speed.getX(), speed.getY());
//        } else {
//            speed = new Double(speed.getX(), 0);
//            //position.setLocation(oldPosition);
//        }

        if(getPosition().getX()*3 - world.getCamera().getPosition().getX() > 240) {
            world.getCamera().getPosition().setLocation(getPosition().getX()*3 - 240, 0);
        }
    }

    @Override
    public Rectangle2D getBounds() {
        return new Rectangle2D.Double(position.getX()+2, position.getY()+16, 13, 16);
    }

    @Override
    public AffineTransform getTransform() {
        AffineTransform tx = super.getTransform();
        if(speed.getX() < 0) {
            tx.translate(getImage().getWidth(), 0);
            tx.scale(-1, 1);
        }

        return tx;
    }
}
