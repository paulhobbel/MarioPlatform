package me.paulhobbel.engine.graphics.renderer;

import me.paulhobbel.engine.graphics.Renderer;
import me.paulhobbel.engine.hud.Hud;
import me.paulhobbel.engine.hud.HudElement;
import me.paulhobbel.engine.hud.HudManager;

import java.awt.*;

public class HudRenderer implements Renderer {
    private static HudRenderer instance;

    public static HudRenderer getInstance() {
        if(instance == null) instance = new HudRenderer();
        return instance;
    }

    private HudManager manager;

    private HudRenderer() {
        manager = HudManager.getInstance();
    }

    @Override
    public void render(Graphics2D g2d) {
        for(Hud hud : manager.getHuds()) {
            for(HudElement element : hud.getElements()) {
                element.draw(g2d);
            }
        }
    }
}
