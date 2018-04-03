package me.paulhobbel.engine.hud;

import java.util.ArrayList;

public class Hud {
    private ArrayList<HudElement> elements = new ArrayList<>();

    public void addElement(HudElement element) {
        elements.add(element);
    }

    public void removeElement(int index) {
        elements.remove(index);
    }

    public void removeElement(HudElement element) {
        elements.remove(element);
    }

    public ArrayList<HudElement> getElements() {
        return elements;
    }
}
