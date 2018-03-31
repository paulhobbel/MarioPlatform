package me.paulhobbel.marioplatform.collision;

import me.paulhobbel.engine.physics.box2d.Contact;
import me.paulhobbel.engine.physics.box2d.ContactListener;
import me.paulhobbel.engine.physics.box2d.Fixture;
import me.paulhobbel.marioplatform.MarioGame;
import me.paulhobbel.marioplatform.entities.Goomba;
import me.paulhobbel.marioplatform.objects.InteractiveObject;
import org.jbox2d.callbacks.ContactImpulse;
import org.jbox2d.collision.Manifold;

public class WorldContactListener implements ContactListener {
    @Override
    public void beginContact(Contact contact) {
        Fixture fixA = contact.getFixtureA();
        Fixture fixB = contact.getFixtureB();

        int cDef = fixA.getFilterData().categoryBits | fixB.getFilterData().categoryBits;

        if(fixA.getUserData() == "head" || fixB.getUserData() == "head") {
            Fixture head = fixA.getUserData() == "head" ? fixA : fixB;
            Fixture object = head == fixA ? fixB : fixA;

            if(object.getUserData() instanceof InteractiveObject) {
                ((InteractiveObject) object.getUserData()).onHeadHitEnter();
            }
        }

        switch (cDef) {
            case MarioGame.ENEMY_HEAD_BIT | MarioGame.MARIO_BIT:
                if(fixA.getFilterData().categoryBits == MarioGame.ENEMY_HEAD_BIT)
                    ((Goomba) fixA.getUserData()).hitOnHead();
                else
                    ((Goomba) fixB.getUserData()).hitOnHead();
                break;
            case MarioGame.ENEMY_BIT | MarioGame.OBJECT_BIT:
                if(fixA.getFilterData().categoryBits == MarioGame.ENEMY_BIT)
                    ((Goomba) fixA.getUserData()).reverseVelocity(true, false);
                else
                    ((Goomba) fixB.getUserData()).reverseVelocity(true, false);
                break;
            case MarioGame.ENEMY_BIT :
                //if(fixA.getFilterData().categoryBits == MarioGame.ENEMY_BIT)
                    ((Goomba) fixA.getUserData()).reverseVelocity(true, false);
                    ((Goomba) fixB.getUserData()).reverseVelocity(true, false);
                break;
            case MarioGame.MARIO_BIT | MarioGame.ENEMY_BIT:
                System.out.println("Mario died!");
        }
    }

    @Override
    public void endContact(Contact contact) {
        Fixture fixA = contact.getFixtureA();
        Fixture fixB = contact.getFixtureB();

        if(fixA.getUserData() == "head" || fixB.getUserData() == "head") {
            Fixture head = fixA.getUserData() == "head" ? fixA : fixB;
            Fixture object = head == fixA ? fixB : fixA;

            if(object.getUserData() instanceof InteractiveObject) {
                ((InteractiveObject) object.getUserData()).onHeadHitLeave();
            }
        }
    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
