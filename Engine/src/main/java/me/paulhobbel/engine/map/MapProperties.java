package me.paulhobbel.engine.map;

import java.util.HashMap;

public class MapProperties extends HashMap<String, Object>{

    public <T> T get(String key, Class<T> type) {
        return (T) get(key);
    }

}
