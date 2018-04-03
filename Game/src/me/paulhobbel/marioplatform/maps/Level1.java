package me.paulhobbel.marioplatform.maps;

import me.paulhobbel.marioplatform.MarioGame;

public class Level1 extends Level {
    public Level1() {
        super("/maps/level1.json");
    }

    @Override
    public void onLevelEnd() {
        MarioGame.getInstance().setLevel(new Level2());
    }
}
