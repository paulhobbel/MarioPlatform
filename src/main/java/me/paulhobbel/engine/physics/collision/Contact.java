package me.paulhobbel.engine.physics.collision;

public class Contact {
    private Collidable collidable;
    private ContactType type;

    public enum ContactType {
        NONE, TOP, BOTTOM, LEFT, RIGHT
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
