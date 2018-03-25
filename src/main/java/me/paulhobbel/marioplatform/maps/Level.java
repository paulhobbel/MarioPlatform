package me.paulhobbel.marioplatform.maps;

import me.paulhobbel.engine.Engine;
import me.paulhobbel.engine.GameObject;
import me.paulhobbel.engine.GameWorld;
import me.paulhobbel.engine.component.DebugComponent;
import me.paulhobbel.engine.component.MapComponent;
import me.paulhobbel.engine.map.MapLayer;
import me.paulhobbel.engine.map.MapObject;
import me.paulhobbel.marioplatform.objects.Ground;
import me.paulhobbel.marioplatform.objects.Pipe;

import java.awt.Color;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;

class Level extends GameObject {
    Level(String mapFile) {
        super();
        MapComponent component = new MapComponent(mapFile, this);
        addComponent(component);

        loadObjects(component);

        setScale(3);
    }

    private void loadObjects(MapComponent component) {
        GameWorld world = Engine.getInstance().getWorld();

        for(MapLayer layer : component.getMap().getLayers()) {
            for(MapObject object : layer.getObjects()) {
                String type = object.getProperties().get("type", String.class);
                int x = object.getProperties().get("x", Integer.class);
                int y = object.getProperties().get("y", Integer.class);

                Point2D position = new Point2D.Double(x / 100, -y / 100);

                if(type.equals("Ground")) {
                    System.out.println(position);
                    world.addObject(new Ground(position, object.getShape()));
                } else if(type.equals("Pipe")) {
                    world.addObject(new Pipe(position, object.getShape()));
                } else {
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
