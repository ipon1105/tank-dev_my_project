package main.com.world.game;


import com.sun.org.apache.xpath.internal.operations.Bool;
import main.com.world.IO.Input;
import main.com.world.display.Display;
import main.com.world.game.Players.FirstMes;
import main.com.world.game.level.FieldMap;
import main.com.world.graphics.TextureAtlas;
import main.com.world.setting.Setting;
import main.com.world.utils.Time;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class GameClient implements Runnable {
    public static final String TITLE = "Танки";

    public static final float UPDATE_RATE = 60.0f;
    public static final float UPDATE_INTERVAL = Time.SECOND / UPDATE_RATE;
    public static final long IDLE_TIME = 1;

    public static final String ATLAS_FILENAME = "resource.png";

    private Boolean running;
    private Thread gameThread;
    private Graphics2D graphics;
    private Input input;
    private TextureAtlas atlas;
    private JoinPlayClient play;
    private FieldMap fieldMap;

    static private ObjectOutputStream output;
    static private ObjectInputStream inputStream;
    static private Socket connection;
    static Thread thread;
    private static Boolean run = false;
    private static int TIP = 0;

    public GameClient() {
        new Thread(this).start();
    }

    public synchronized void start() {
        if (running) return;

        graphics = Display.getGraphics();
        Display.addInputListener(input);

        running = true;
        //client.start();
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

    private void update(Object o) {
        render();
    }

    private void render() {
        Display.clear();

        fieldMap.render(graphics);
        play.render(graphics);
        Display.swapBuffers();
    }

    public void run() {
        Boolean f = true;
        while (run) {
            try {
                //output = new ObjectOutputStream(connection.getOutputStream());
                while (run) {
                    connection = new Socket(InetAddress.getByName(Setting.IP4), Setting.PORT);
                    inputStream = new ObjectInputStream(connection.getInputStream());
                    if (f) {
                        play = new JoinPlayClient((FirstMes)inputStream.readObject());
                        return;
                    }
                    update(inputStream.readObject());
                }
            } catch (Exception e) {

            }
        }
    }

    private void cleanUp() {
        Display.createMenu();
    }


        /*
        client = new Client();
        running = false;
        input = new Input();

        atlas = new TextureAtlas(ATLAS_FILENAME);
        fieldMap = new FieldMap(atlas);
        play = new JoinPlayClient(atlas,fieldMap, client);
        */

        /*
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

*/

    public static class Client implements Runnable {

        public static int getTIP(){
            if (TIP==0) return 0;
            int a = TIP;
            TIP = 0;
            return a;
        }

        public Client(){
            run = false;
        }

        public void start(){
            if (run) return;
            run = true;

            thread = new Thread(this::run);
            thread.start();
        }

        public void stop(){
            if (!run) return;
            run = false;
            try{
                thread.join(300);
            }catch (Exception e){
                //МДА
            }
        }

        @Override
        public void run() {

        }

        public static void setData(Object obj){
            try {
                output.flush();
                output.writeObject(obj);
                System.err.println("Отправка.");
            } catch (IOException e){
                System.err.println("Ошибка отправки.");
            }

        }

        public static Boolean getRun(){
            return run;
        }
    }
}

