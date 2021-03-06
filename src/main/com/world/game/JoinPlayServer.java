package main.com.world.game;

import main.com.world.Counter;
import main.com.world.IO.Input;
import main.com.world.game.Players.Controller;
import main.com.world.game.Players.Player;
import main.com.world.game.Players.bullet.Bullet;
import main.com.world.game.Players.bullet.BulletManager;
import main.com.world.game.Players.viewer.ImageViewerA;
import main.com.world.game.Players.viewer.ImageViewerB;
import main.com.world.game.level.FieldMap;
import main.com.world.graphics.TextureAtlas;
import main.com.world.online.Server;
import main.com.world.setting.Setting;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class JoinPlayServer {
    public BulletManager bulletManager;
    public ArrayList<Bullet> bulletsList_A;
    public ArrayList<Bullet> bulletsList_B;
    public float FFF = 0;
    public static int killA= 0;
    public static int killB= 0;
    private Player A;
    private Player B;
    Server server;

    public JoinPlayServer(TextureAtlas atlas, FieldMap map, Server server){
        this.server = server;
        Controller conA = new Controller(KeyEvent.VK_W,KeyEvent.VK_A,KeyEvent.VK_S,KeyEvent.VK_D, KeyEvent.VK_1,KeyEvent.VK_2,KeyEvent.VK_3,KeyEvent.VK_SPACE,(byte)0,false);
        Controller conB = new Controller(KeyEvent.VK_UP,KeyEvent.VK_LEFT,KeyEvent.VK_DOWN,KeyEvent.VK_RIGHT, KeyEvent.VK_NUMPAD1,KeyEvent.VK_NUMPAD2,KeyEvent.VK_NUMPAD3,KeyEvent.VK_NUMPAD5,(byte)1,true);

        ImageViewerA imA = new ImageViewerA(Setting.SCALE_A);
        ImageViewerB imB = new ImageViewerB(Setting.SCALE_B);

        A = new Player(Setting.SPEED_A, imA, map,conA);
        B = new Player(Setting.SPEED_B, imB, map,conB);

        bulletManager = new BulletManager(atlas,map,A,B);

        A.setBulletManager(bulletManager);
        B.setBulletManager(bulletManager);

        Counter.KILL_A = 0;
        Counter.KILL_B = 0;
    }

    public void render(Graphics2D g){
        A.render(g);
        B.render(g);

        bulletManager.render(g);
    }

    public void update(Input input){
        bulletManager.update();

        A.update(input);
        B.update(input, server.getTIP());

    }
}
