package me.paulhobbel.engine;

import me.paulhobbel.engine.graphics.Camera;
import me.paulhobbel.engine.graphics.renderer.CollisionRenderer;
import me.paulhobbel.engine.graphics.renderer.DebugDraw;
import me.paulhobbel.engine.physics.box2d.World;
import me.paulhobbel.engine.physics.collisionOld.Collidable;
import me.paulhobbel.engine.physics.collisionOld.ContactListener;
import org.jbox2d.common.Vec2;

import java.awt.geom.Area;
import java.util.ArrayList;

public class GameWorld {
    private Camera camera;

    private GameObjects objects = new GameObjects();

    private World physicsWorld;

    GameWorld() {
        camera = new Camera();
        physicsWorld = new World(new Vec2(0f, 3.8f), true);
        //Box2DDebugRenderer.getInstance().setActiveWorld(physicsWorld);
        DebugDraw.getInstance().setScale(3);
        DebugDraw.getInstance().setWorld(physicsWorld);
    }

    public void addObject(GameObject object) {
        objects.add(object);
        object.start();
        object.resume();
    }

    public Camera getCamera() {
        return camera;
    }

    public World getPhysicsWorld() {
        return physicsWorld;
    }

    /**
     * Update all objects of this world
     * @param elapsedTime Elapsed time since last update
     */
    public void update(double elapsedTime) {
        physicsWorld.step(1/60f, 6, 2);

        for(GameObject object : objects) {
            object.update(elapsedTime);
        }
        //tick();
    }

    public boolean hasCollision(double x, double y) {
        ArrayList<Collidable> collidables = objects.getByType(Collidable.class);

        for(Collidable collidable1: collidables) {
            if(collidable1.getBounds().contains(x, y)) {
                return true;
            }
        }
        return false;
    }

    public boolean hasCollision(double x, double y, double width, double height) {
        ArrayList<Collidable> collidables = objects.getByType(Collidable.class);

        for(Collidable collidable1: collidables) {
            if(collidable1.getBounds().intersects(x, y, width, height)) {
                return true;
            }
        }
        return false;
    }

    public boolean hasCollision(Collidable collidable) {
        //ArrayList<Collidable> collidables = objects.getByType(Collidable.class);
        //collidables.sort(Collidable.compareDistance(collidable.getPosition()));

//        for(Collidable collidable1: collidables) {
//            if(collidable1.getBounds().intersects(collidable.getBounds().getBounds2D()) && !collidable1.equals(collidable)) {
////                if(ContactListener.class.isInstance(collidable)) {
////                    ((ContactListener) collidable).onCollision(new Contact(collidable1, ContactType.BOTTOM));
////                }
//                return true;
//            }
//        }
        return false;
    }

    private void tick() {
        ArrayList<ContactListener> contactListeners = objects.getByType(ContactListener.class);
        ArrayList<Collidable> collidables = objects.getByType(Collidable.class);

        for(ContactListener contactListener : contactListeners) {
            for(Collidable collidable : collidables) {
                if(collidable.equals(contactListener)) continue;

                // Check contactListener partly intersects with collidable
                if(collidable.getBounds().intersects(contactListener.getBounds().getBounds2D())) {

                    Area collisionBox = new Area(contactListener.getBounds());
                    collisionBox.intersect(new Area(collidable.getBounds()));

                    CollisionRenderer.getInstance().addShape(collisionBox);

                    //System.out.println(collisionBox);
                }
//
//                    Area bounds = new Area(collidable.getBounds());
//
//                    bounds.
//
//                    ArrayList<ContactType> contacts = new ArrayList<>();
//
//                    ContactType contactType = ContactType.NONE;
//
//                    if(collidable.getBounds().contains(collisionBox.getX()+collisionBox.getWidth(), collisionBox.getY())) {
//                        contacts.add(ContactType.BOTTOM_LEFT);
//                    }
//
//                    if(collidable.getBounds().contains(collisionBox.getX(), collisionBox.getY())) {
//                        contacts.add(ContactType.BOTTOM_RIGHT);
//                    }
//
//                    if(collidable.getBounds().contains(collisionBox.getX()+collisionBox.getWidth(), collisionBox.getY()+collisionBox.getHeight())) {
//                        contacts.add(ContactType.TOP_LEFT);
//                    }
//
//                    if(collidable.getBounds().contains(collisionBox.getX(), collisionBox.getY()+collisionBox.getHeight())) {
//                        contacts.add(ContactType.TOP_RIGHT);
//                    }
//
//                    if(contacts.size() == 0) {
//                        contacts.add(ContactType.NONE);
//                    } else {
//                        if (contacts.containsAll(ContactType.COMBINED_TYPES.get(ContactType.INSIDE))) {
//                            contacts.removeAll(ContactType.COMBINED_TYPES.get(ContactType.INSIDE));
//                            contacts.add(ContactType.INSIDE);
//                        } else if (contacts.containsAll(ContactType.COMBINED_TYPES.get(ContactType.LEFT))) {
//                            contacts.removeAll(ContactType.COMBINED_TYPES.get(ContactType.LEFT));
//                            contacts.add(ContactType.LEFT);
//                        } else if (contacts.containsAll(ContactType.COMBINED_TYPES.get(ContactType.RIGHT))) {
//                            contacts.removeAll(ContactType.COMBINED_TYPES.get(ContactType.RIGHT));
//                            contacts.add(ContactType.RIGHT);
//                        } else if (contacts.containsAll(ContactType.COMBINED_TYPES.get(ContactType.BOTTOM))) {
//                            contacts.removeAll(ContactType.COMBINED_TYPES.get(ContactType.BOTTOM));
//                            contacts.add(ContactType.BOTTOM);
//                        } else if (contacts.containsAll(ContactType.COMBINED_TYPES.get(ContactType.TOP))) {
//                            contacts.removeAll(ContactType.COMBINED_TYPES.get(ContactType.TOP));
//                            contacts.add(ContactType.TOP);
//                        }
//                    }
//
//                    contactListener.onCollision(new Contact(collidable, contacts.get(0)));
//                }
            }
        }
    }
}
