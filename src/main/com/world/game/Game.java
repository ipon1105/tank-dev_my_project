package main.com.world.game;


import main.com.world.Counter;
import main.com.world.IO.Input;
import main.com.world.display.Display;
import main.com.world.game.level.FieldMap;
import main.com.world.utils.Time;
import main.com.world.graphics.TextureAtlas;

import java.awt.*;

public class Game implements Runnable {
    public static Boolean FIRSTMATCH = false;
    public static final int HEIGHT = 630;
    public static final int WIDTH = 798;
    public static final String TITLE = "Танки";
    public static final int CLEAR_COLOR = 0xff000000;
    public static final int NUM_BUFFERS = 2;

    public static final float UPDATE_RATE = 60.0f;
    public static final float UPDATE_INTERVAL = Time.SECOND / UPDATE_RATE;
    public static final long IDLE_TIME = 1;

    public static final String ATLAS_FILENAME = "resource.png";

    private int map[][];

    private Boolean running;
    private Thread gameThread;
    private Graphics2D graphics;
    private Input input;
    private TextureAtlas atlas;
    private JoinPlay play;
    private FieldMap fieldMap;

    public Game(int map[][]) {
        this.map = map;

        running = false;
        input = new Input();

        atlas = new TextureAtlas(ATLAS_FILENAME);
        fieldMap = new FieldMap(atlas, map);
        play = new JoinPlay(atlas,fieldMap);
    }

    public synchronized void start() {
        if (running) return;

        graphics = Display.getGraphics();
        Display.addInputListener(input);

        running = true;
        gameThread = new Thread(this);
        gameThread.start();
    }

    public synchronized void stop() {
        if (!running) return;

        running = false;
        try {
            gameThread.join(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        cleanUp();
    }

    private void update() {
        play.update(input);
    }

    private void render(String word) {
        Display.clear();
        graphics.drawString(word, Game.WIDTH / 2 - 30, Game.HEIGHT / 2);
        Display.swapBuffers();
    }
    private void render() {
        Display.clear();

        if (Counter.KILL_B >= 50){
            render("Комнада B победила");
            return;
        } else if (Counter.KILL_A >= 50){
            render("Комнада A победила");
            return;
        }

        fieldMap.render(graphics);
        play.render(graphics);
        Display.updateCount();

        Display.swapBuffers();
    }

    public void run() {
        int fps = 0;
        int upd = 0;
        int updl = 0;

        long count = 0;

        float delta = 0;

        long lastTime = Time.get();
        while (running) {
            long now = Time.get();
            long elapsedTime = now - lastTime;
            lastTime = now;

            count+=elapsedTime;

            Boolean render = false;
            delta += (elapsedTime / UPDATE_INTERVAL);
            while (delta > 1) {
                update();
                upd++;
                delta--;
                if (render) {
                    updl++;
                } else {
                    render = true;
                }
            }

            if (render) {
                render();
                fps++;
            } else {
                try {
                    Thread.sleep(IDLE_TIME);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            if (count>=Time.SECOND){
                Display.setTitle(TITLE + "||  FPS: " + fps + " |  UPD: "+upd+ " | UPDL: " + updl);
                upd = 0;
                updl = 0;
                fps = 0;
                count = 0;
            }

        }


    }

    private void cleanUp() {
        FIRSTMATCH = true;
        Display.createMenu();
    }
}

