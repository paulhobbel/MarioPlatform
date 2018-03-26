package me.paulhobbel.engine;

import me.paulhobbel.engine.map.MapObject;
import me.paulhobbel.engine.physics.Collidable;

import java.util.ArrayList;

public class GameObjects extends ArrayList<GameObject> {
    /**
     * Get all objects whose match given type
     * @param type The type
     * @return all the objects in the collection matching type
     */
    @SuppressWarnings("unchecked")
    public <T> ArrayList<T> getByType(Class<T> type) {
        ArrayList<T> result = new ArrayList<>();
        for(int i = 0; i < size(); i++) {
            GameObject object = get(i);
            if(type.isInstance(object)) {
                result.add((T) object);
            }
        }

        return result;
    }
}
