package me.paulhobbel.marioplatform.maps;

import me.paulhobbel.engine.Engine;
import me.paulhobbel.engine.GameObject;
import me.paulhobbel.engine.GameWorld;
import me.paulhobbel.engine.component.DebugComponent;
import me.paulhobbel.engine.map.MapLayer;
import me.paulhobbel.engine.map.MapObject;
import me.paulhobbel.engine.objects.Map;
import me.paulhobbel.marioplatform.entities.Goomba;
import me.paulhobbel.marioplatform.objects.Ground;
import me.paulhobbel.marioplatform.objects.Pipe;

import java.awt.Color;
import java.awt.geom.Point2D;

class Level extends Map {
    Level(String mapFile) {
        super(mapFile);

        loadObjects();

        setScale(3);
    }

    private void loadObjects() {
        GameWorld world = Engine.getInstance().getWorld();

        for(MapLayer layer : getMap().getLayers()) {
            for(MapObject object : layer.getObjects()) {
                String type = object.getProperties().get("type", String.class);
                int x = object.getProperties().get("x", Integer.class);
                int y = object.getProperties().get("y", Integer.class);

                Point2D position = new Point2D.Double(x, y);

                switch (type) {
                    case "Ground":
                        world.addObject(new Ground(position, object.getShape()));
                        break;
                    case "Pipe":
                        world.addObject(new Pipe(position, object.getShape()));
                        break;
                    case "Goomba":
                        world.addObject(new Goomba(position));
                        break;
                    default:
                        GameObject gameObject = new GameObject(position);
                        gameObject.addComponent(new DebugComponent(object.getShape(), Color.RED, gameObject));
                        gameObject.setScale(3);
                        world.addObject(gameObject);

                        System.err.println("Added unknown object with type '" + type + "' to world");
                }
            }
        }
    }
}
