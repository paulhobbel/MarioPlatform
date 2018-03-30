package me.paulhobbel.engine.core;

import me.paulhobbel.engine.input.Input;

public class GameLoop implements Runnable {

    private Engine engine;

    private Thread thread;
    private boolean running = false;

    GameLoop(Engine engine) {
        this.engine = engine;
    }

    void start() {
        thread = new Thread(this);
        thread.run();
    }

    void stop() {
        running = false;
    }

    @Override
    public void run() {
        running = true;

        boolean render = false;
        double firstTime;
        double lastTime = System.nanoTime() / 1e9;
        double passedTime;
        double unprocessedTime = 0;

        double frameTime = 0;
        double frames = 0;
        double fps = engine.settings.fps;

        while (running) {

            firstTime = System.nanoTime() / 1e9;
            passedTime = firstTime - lastTime;
            lastTime = firstTime;

            unprocessedTime += passedTime;
            frameTime += passedTime;

            while(unprocessedTime >= 1.0 / engine.settings.fps) {
                unprocessedTime -= 1.0 / engine.settings.fps;
                render = true;

                // TODO: Update game
                engine.activeWorld.update(1.0 / (engine.settings.fps * (1000 * passedTime)));
                engine.window.setTitle(String.format("%s FPS: %.2f", engine.settings.title, fps / (1000 * passedTime)));
                Input.getInstance().update();

                if(frameTime >= 1.0) {
                    frameTime = 0;
                    fps = frames;
                    frames = 0;

                    System.out.println("FPS: " + fps);
                }
            }

            if(render) {
                engine.window.update();
                frames++;
                render = false;
            }

            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
