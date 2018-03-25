package me.paulhobbel.marioplatform.maps;

import me.paulhobbel.engine.GameObject;
import me.paulhobbel.engine.component.MapComponent;

class Level extends GameObject {
    Level(String mapFile) {
        super();
        addComponent(new MapComponent(mapFile, this));
        setScale(3);
    }
}
