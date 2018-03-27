package me.paulhobbel.engine.physics.collisionOld;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Contact {
    private Collidable collidable;
    private ContactType type;

    public enum ContactType {
        INSIDE,
        TOP, BOTTOM,
        LEFT, RIGHT,
        TOP_LEFT, TOP_RIGHT,
        BOTTOM_LEFT, NONE, BOTTOM_RIGHT;

        public static final HashMap<ContactType, List<ContactType>> COMBINED_TYPES = new HashMap<>();

        static {
            COMBINED_TYPES.put(INSIDE, Arrays.asList(BOTTOM_LEFT, BOTTOM_RIGHT, TOP_LEFT, TOP_RIGHT));
            COMBINED_TYPES.put(TOP, Arrays.asList(TOP_LEFT, TOP_RIGHT));
            COMBINED_TYPES.put(BOTTOM, Arrays.asList(BOTTOM_LEFT, BOTTOM_RIGHT));
            COMBINED_TYPES.put(LEFT, Arrays.asList(BOTTOM_LEFT, TOP_LEFT));
            COMBINED_TYPES.put(RIGHT, Arrays.asList(BOTTOM_RIGHT, TOP_RIGHT));
        }
    }

    public Contact(Collidable collidable, ContactType type) {
        this.collidable = collidable;
        this.type = type;
    }

    public Collidable getCollidable() {
        return collidable;
    }

    public ContactType getType() {
        return type;
    }

    @Override
    public String toString() {
        return getClass().getName() + "[collidable=" + collidable + ",type=" + type + "]";
    }
}
