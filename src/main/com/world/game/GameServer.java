package main.com.world.game;


import main.com.world.IO.Input;
import main.com.world.display.Display;
import main.com.world.game.level.FieldMap;
import main.com.world.graphics.TextureAtlas;
import main.com.world.online.Server;
import main.com.world.utils.Time;

import java.awt.*;
import java.awt.event.KeyEvent;

public class GameServer implements Runnable {
    //private static Logger log = Logger.getLogger(Game.class.getName());
    public static final int HEIGHT = 630;
    public static final int WIDTH = 798;
    public static final String TITLE = "Танки";
    public static final int CLEAR_COLOR = 0xff000000;
    public static final int NUM_BUFFERS = 3;

    public static final float UPDATE_RATE = 60.0f;
    public static final float UPDATE_INTERVAL = Time.SECOND / UPDATE_RATE;
    public static final long IDLE_TIME = 1;

    public static final String ATLAS_FILENAME = "resource.png";

    private Boolean running;
    private Thread gameThread;
    private Graphics2D graphics;
    private Input input;
    private TextureAtlas atlas;
    private JoinPlayServer play;
    private FieldMap fieldMap;
    Server server;

    public GameServer() {
        server = new Server();
        running = false;
        input = new Input();

        atlas = new TextureAtlas(ATLAS_FILENAME);
        fieldMap = new FieldMap();
        play = new JoinPlayServer(atlas,fieldMap,server);
    }

    public synchronized void start() {
        if (running) return;

        graphics = Display.getGraphics();
        Display.addInputListener(input);

        running = true;
        server.start();
        gameThread = new Thread(this);
        gameThread.start();
    }

    public synchronized void stop() {
        if (!running) return;

        running = false;
        try {
            server.stop();
            gameThread.join(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        cleanUp();
    }

    private void update() {
        if (input.getKey(KeyEvent.VK_ESCAPE)) stop();

        play.update(input);
    }

    private void render() {
        Display.clear();

        fieldMap.render(graphics);
        play.render(graphics);
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
        Display.createMenu();
    }
}

