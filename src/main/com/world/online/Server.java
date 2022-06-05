package main.com.world.online;

import main.com.world.setting.Setting;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server implements Runnable{
    static private ObjectOutputStream output;
    static private ObjectInputStream input;
    static private Socket connection;
    static private ServerSocket server;
    static Thread thread;
    private static Boolean run = false;
    private int TIP = 0;

    public Server(){
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
            thread.join(3);
        }catch (Exception e){
            //МДА
        }
    }

    @Override
    public void run() {
        try {
            server = new ServerSocket(Setting.PORT, Setting.BACKLOG);
            while (run){
                connection = server.accept();

                output = new ObjectOutputStream(connection.getOutputStream());
                input = new ObjectInputStream(connection.getInputStream());

                TIP = (int) input.readObject();
                setData(0);
            }
        } catch (Exception e){

        }
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
    public int getTIP(){
        if (TIP==0) return 0;
        int a = TIP;
        TIP =0;
        return a;
    }

    public static Boolean getRun(){
        return run;
    }

}
