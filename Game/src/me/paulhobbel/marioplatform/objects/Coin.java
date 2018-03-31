package me.paulhobbel.marioplatform.objects;

import me.paulhobbel.engine.map.tiled.TiledMap;
import me.paulhobbel.engine.map.tiled.TiledMapTileSet;
import me.paulhobbel.marioplatform.MarioGame;

import java.awt.*;

public class Coin extends InteractiveObject {
    private TiledMapTileSet tileSet;

    public Coin(TiledMap map, Rectangle bounds) {
        super(map, bounds);
        fixture.setUserData(this);
        setCategoryFilter(MarioGame.COIN_BIT);

        tileSet = map.getTileSets().getTileSet("tileset_gutter");
    }

    @Override
    public void onHeadHitEnter() {
        System.out.println("Collision: Coin");
        getCell().setTile(tileSet.getTile(28));
    }

    @Override
    public void onHeadHitLeave() {

    }
}