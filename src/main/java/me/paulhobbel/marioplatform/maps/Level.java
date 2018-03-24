package me.paulhobbel.marioplatform.maps;

import me.paulhobbel.engine.GameObject;
import me.paulhobbel.engine.component.Map;

class Level extends GameObject {
    Level(String mapFile) {
        super();
        addComponent(new Map(mapFile, this));
        setScale(3);
    }
}
