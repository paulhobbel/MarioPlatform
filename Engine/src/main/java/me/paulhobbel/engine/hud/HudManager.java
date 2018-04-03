package me.paulhobbel.engine.hud;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HudManager {
    private static HudManager instance;

    public static HudManager getInstance() {
        if(instance == null) instance = new HudManager();
        return instance;
    }

    private Map<String, Hud> huds = new HashMap<>();

    private HudManager() {

    }

    public List<Hud> getHuds() {
        return new ArrayList<>(huds.values());
    }

    public HudManager addHud(String name, Hud hud) {
        huds.put(name, hud);
        return this;
    }

    public HudManager removeHud(String name) {
        huds.remove(name);
        return this;
    }

    public void disableHud(String name) {
        // TODO: Create a way to enable/disable hud's
    }
}
