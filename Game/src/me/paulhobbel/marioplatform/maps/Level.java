package me.paulhobbel.marioplatform.maps;

import me.paulhobbel.engine.map.MapLayer;
import me.paulhobbel.engine.map.MapObject;
import me.paulhobbel.engine.objects.Map;
import me.paulhobbel.marioplatform.MarioGame;
import me.paulhobbel.marioplatform.entities.Goomba;
import me.paulhobbel.marioplatform.entities.Mario;
import me.paulhobbel.marioplatform.objects.*;
import org.jbox2d.common.Vec2;

public abstract class Level extends Map {
    Level(String mapFile) {
        super(mapFile);

        world.resetWorld();
        loadObjects();

        setScale(3);
    }

    private void loadObjects() {

        for(MapLayer layer : getMap().getLayers()) {
            for(MapObject object : layer.getObjects()) {
                String type = object.getProperties().get("type", String.class);
                int x = object.getProperties().get("x", Integer.class);
                int y = object.getProperties().get("y", Integer.class);

                Vec2 position = new Vec2(x, y);

                switch (type) {
                    case "Ground":
                        new Ground(object.getShape().getBounds());
                        break;
                    case "Pipe":
                        new Pipe(object.getShape().getBounds());
                        break;
                    case "Brick":
                        new Brick(getMap(), object.getShape().getBounds());
                        break;
                    case "Coin":
                        new Coin(getMap(), object.getShape().getBounds());
                        break;
                    case "Goomba":
                        world.addObject(new Goomba(position));
                        break;
                    case "Mario":
                        world.addObject(new Mario(position));
                        break;
                    case "End":
                        new End(object.getShape().getBounds());
                        break;
                    default:
//                        GameObject gameObject = new GameObject(position);
//                        gameObject.setScale(3);
//                        world.addObject(gameObject);

                        System.err.println("Found unknown object with type '" + type + "', not adding to world!");
                }
            }
        }
    }

    public abstract void onLevelEnd();
}
