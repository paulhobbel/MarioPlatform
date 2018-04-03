package me.paulhobbel.engine.core;

import me.paulhobbel.engine.physics.box2d.Body;
import me.paulhobbel.engine.physics.box2d.BodyDef;
import me.paulhobbel.engine.physics.box2d.Geometry;
import me.paulhobbel.engine.physics.box2d.World;
import me.paulhobbel.engine.window.GameWindow;
import me.paulhobbel.engine.window.WindowManager;
import org.jbox2d.dynamics.BodyType;

import java.util.ArrayList;

public class Engine implements Disposable {
    private static Engine instance;

    public static Engine getInstance() {
        if(instance == null) instance = new Engine();
        return instance;
    }

    Application application;
    GameWindow window;

    private GameLoop loop;

    public final EngineSettings settings = new EngineSettings();

    // TODO: Move to EngineSettings
    public static final float PPM = 100;


    private ArrayList<GameWorld> worlds = new ArrayList<>();
    GameWorld activeWorld;

    public Engine() {
        loop = new GameLoop(this);

        // TODO: Create some better logic for this
        worlds.add(new GameWorld());
        activeWorld = worlds.get(0);
    }

    private void start() {
        window = WindowManager.getInstance().createWindow(settings.width, settings.height);
        window.setTitle(settings.title);
        window.setVisible(true);

        loop.start();
    }

    public void stop() {
        loop.stop();
    }

    public GameWorld getActiveWorld() {
        return activeWorld;
    }

    public void setApplication(Application application) {
        this.application = application;
        this.application.init();
        start();
    }

    @Override
    public void dispose() {

    }
}
