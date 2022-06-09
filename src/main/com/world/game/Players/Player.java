package main.com.world.game.Players;

import main.com.world.Counter;
import main.com.world.IO.Input;
import main.com.world.display.Display;
import main.com.world.game.Game;
import main.com.world.game.GameClient;
import main.com.world.game.Players.bullet.BulletManager;
import main.com.world.game.Players.viewer.ImageViewer;
import main.com.world.game.level.FieldMap;
import main.com.world.online.Server;
import main.com.world.setting.Setting;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Player extends GameObjects {
    public Boolean life;
    public float scale;
    public float speed;
    public FieldMap m;
    public int way;


    private BulletManager bulletManager;
    private final ImageViewer viewer;
    private float newX;
    private float newY;
    private float ReloadTime = 100;
    private int bulletMod = 2;
    private int nowTime;
    private final Controller controller;
    private final byte colorTank;

    public Player(float speed, float scale, ImageViewer viewer, FieldMap lvl, Controller controller) {
        super(0, 0, null);
        this.colorTank = controller.getColor();
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.scale = scale;
        this.viewer = viewer;
        this.controller = controller;

        m = lvl;
        setsSpawn();
        life = true;
        image = viewer.getNowImage(0);
    }
    public void setsSpawn(){
        float[] res;
        switch (colorTank){
            case 0:
                res = m.getSpawnA();
                break;
            case 1:
                res = m.getSpawnB();
                break;
            default:res = new float[]{0,0};
            break;
        }

        x = res[0];
        y = res[1];
    }

    public void setBulletManager(BulletManager bulletManager) {
        this.bulletManager = bulletManager;
    }

    public int getWay(){
        return way;
    }

    public void BulletMod(Input input){
        if (controller.getOnline()) return;

        if (input.getKey(controller.get_1())) {
            setWeapon1();
        } else if (input.getKey(controller.get_2())) {
            setWeapon2();
        } else if (input.getKey(controller.get_3())) {
            setWeapon3();
        }

        if ((input.getKey(controller.getSHOOT())) && (nowTime >= ReloadTime)) {
            Shoot();
        }
    }

    public void BulletUpdates(Input input) {

        if (nowTime < ReloadTime) nowTime++;
        BulletMod(input);
    }

    public void MoveUp(){//если он идёт вверх
        way = 0;
        image = viewer.getNowImage(way);

        if ((!m.Collision(this, way))) {
            if (GameClient.Client.getRun()) GameClient.Client.setData(1);
            if (Server.getRun()) Server.setData(1);
            newY -= speed;
        }
    }
    public void MoveRight(){

        //если он идёт вправо
        way = 1;
        image = viewer.getNowImage(way);

        if ((!m.Collision(this, way))) {
            if (GameClient.Client.getRun()) GameClient.Client.setData(2);
            if (Server.getRun()) Server.setData(2);
            newX += speed;
        }

    }
    public void MoveDown(){

        //если он идёт вниз
        way = 2;
        image = viewer.getNowImage(way);

        if ((!m.Collision(this, way))) {
            if (GameClient.Client.getRun()) GameClient.Client.setData(3);
            if (Server.getRun()) Server.setData(3);
            newY += speed;
        }
    }
    public void MoveLeft(){
        //если он идёт влево
        way = 3;
        image = viewer.getNowImage(way);

        if ((!m.Collision(this, way))) {
            if (GameClient.Client.getRun()) GameClient.Client.setData(4);
            if (Server.getRun()) Server.setData(4);
            newX -= speed;
        }

    }
    public void setWeapon1(){
        if (GameClient.Client.getRun()) GameClient.Client.setData(5);
        if (Server.getRun()) Server.setData(5);
        bulletMod = 1;
        ReloadTime = Setting.A_BULLET_COOLDOWN;}
    public void setWeapon2(){
        if (GameClient.Client.getRun()) GameClient.Client.setData(6);
        if (Server.getRun()) Server.setData(6);
        bulletMod = 2;
        ReloadTime = Setting.B_BULLET_COOLDOWN;}
    public void setWeapon3(){
        if (GameClient.Client.getRun()) GameClient.Client.setData(7);
        if (Server.getRun()) Server.setData(7);
        bulletMod = 3;
        ReloadTime = Setting.C_BULLET_COOLDOWN;}
    public void Shoot(){
        Display.playSound(2);
        if (GameClient.Client.getRun()) GameClient.Client.setData(8);
        if (Server.getRun()) Server.setData(8);
        nowTime = 0;
        bulletManager.shoot(this, bulletMod);}

    public void MovesMode(Input input){
        if (controller.online) return;
        if (input.getKey(controller.getW())) {
            MoveUp();
        } else if (input.getKey(controller.getS())) {
            MoveDown();
        } else if (input.getKey(controller.getD())) {
            MoveRight();
        } else if (input.getKey(controller.getA())) {
            MoveLeft();
        }}

    public void MoveUpdates(Input input) {
        MovesMode(input);

        if (newX < 0) {
            newX = 0;
        } else if (newX > (Game.WIDTH - image.getWidth())) {
            newX = Game.WIDTH - image.getWidth();
        } else if (newY < 0) {
            newY = 0;
        } else if (newY > (Game.HEIGHT - image.getHeight())) {
            newY = Game.HEIGHT - image.getHeight();
        }

    }

    public void update(Input input, int a) {
        if (!life) {
            if (!viewer.BoomStart()) {
                Display.playSound(1);
                viewer.StartExplosion();
            }

            if (viewer.BoomFinish()) {
                viewer.CleanExplosion();

                setsSpawn();

                if (controller.SHOOT == KeyEvent.VK_SPACE)
                    Counter.KILL_A++;
                else
                    Counter.KILL_B++;
                Counter.counterPrint();
                image = viewer.getNowImage(0);
                life = true;
            } else {
                way = 0;
                viewer.update();

                image = viewer.getExplosionImage();
                return;
            }
        }

        newX = x;
        newY = y;

        switch (a){
            case 1:
                MoveUp();
                break;
            case 2:
                MoveRight();
                break;
            case 3:
                MoveDown();
                break;
            case 4:
                MoveLeft();
                break;
            case 5:
                setWeapon1();
                break;
            case 6:
                setWeapon2();
                break;
            case 7:
                setWeapon3();
                break;
            case 8:
                Shoot();
                break;
            default:
        }
        BulletUpdates(input);
        MoveUpdates(input);

        x = newX;
        y = newY;

    }

    public void update(Input input) {
        if (!life) {
            if (!viewer.BoomStart()) {
                Display.playSound(1);
                viewer.StartExplosion();
            }

            if (viewer.BoomFinish()) {
                viewer.CleanExplosion();

                setsSpawn();

                if (controller.SHOOT == KeyEvent.VK_SPACE)
                    Counter.KILL_A++;
                else
                    Counter.KILL_B++;
                Counter.counterPrint();
                image = viewer.getNowImage(0);
                life = true;
            } else {
                way = 0;
                viewer.update();

                image = viewer.getExplosionImage();
                return;
            }
        }

        newX = x;
        newY = y;

        BulletUpdates(input);
        MoveUpdates(input);

        x = newX;
        y = newY;

    }

    public void update(Input input, GameClient.Client client) {
        if (!life) {
            if (!viewer.BoomStart()) {
                Display.playSound(1);
                viewer.StartExplosion();
            }

            if (viewer.BoomFinish()) {
                viewer.CleanExplosion();

                setsSpawn();

                Counter.KILL_B++;
                Counter.counterPrint();
                image = viewer.getNowImage(0);
                life = true;
            } else {
                way = 0;
                viewer.update();

                image = viewer.getExplosionImage();
                return;
            }
        }

        newX = x;
        newY = y;

        BulletUpdates(input);
        MoveUpdates(input);

        x = newX;
        y = newY;

    }
    //здесь всё ок
    public void render(Graphics2D g){
        g.drawImage(image, (int) x, (int) y, image.getWidth(), image.getHeight(), null);
        if (hitBox) hitBoxRender(g);
    }

    public String getInfo() {
        return ("Игрок;\t Х = " + getX() + ";\t Y = " + getY() + ";\t X2 = " + getX2() + ";\t Y2 = " + getY2() + ";");
    }

    public int getWidth(){
        return image.getWidth();
    }

    public int getHeight(){
        return image.getHeight();
    }
}
