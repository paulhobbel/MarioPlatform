package me.paulhobbel.engine.map;

import java.util.ArrayList;

public class MapObjects extends ArrayList<MapObject> {

    /**
     * Get MapObject by name
     * @param name The name
     */
    public MapObject get (String name) {
        for (MapObject object : this) {
            if (name.equals(object.getName())) {
                return object;
            }
        }
        return null;
    }

    /**
     * Get all objects whose match given type
     * @param type The type
     * @return all the objects in the collection matching type
     */
    @SuppressWarnings("unchecked")
    public <T extends MapObject> ArrayList<T> getByType(Class<T> type) {
        ArrayList<T> result = new ArrayList<>();
        for(int i = 0; i < size(); i++) {
            MapObject object = get(i);
            if(type.isInstance(object)) {
                result.add((T) object);
            }
        }

        return result;
    }
}
