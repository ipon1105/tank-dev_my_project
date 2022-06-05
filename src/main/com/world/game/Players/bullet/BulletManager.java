package main.com.world.game.Players.bullet;

import main.com.world.game.Game;
import main.com.world.game.Players.Player;
import main.com.world.game.level.FieldMap;
import main.com.world.graphics.TextureAtlas;
import main.com.world.setting.Setting;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class BulletManager {
    private ArrayList<Bullet> bulletList;
    private BulletViewer viewer;
    private FieldMap lvl;
    private Player A;
    private Player B;

    public BulletManager(TextureAtlas atlas, FieldMap lvl, Player A, Player B) {
        this.lvl = lvl;
        this.A = A;
        this.B = B;

        bulletList = new ArrayList<Bullet>();
        viewer = new BulletViewer(atlas);
    }

    public void update() {
        bulletsUpdate();

        bulletStrikeCollision();
        bulletListClear();

        tankStrikeCollision();
        bulletListClear();
    }

    public void bulletListClear(){
        for (int i = 0; i < bulletList.size(); i++) {
            if (!bulletList.get(i).getLive()) {
                bulletList.remove(i);
            }
        }
    }

    public void bulletStrikeCollision() {
        if (bulletList.isEmpty()) {
            return;
        }

        for (int i = 0; i < bulletList.size(); i++) {
            for (int j = 0; j < bulletList.size(); j++) {
                if (i != j) {
                    if (bulletList.get(i).Collision(bulletList.get(j))) {
                        bulletList.get(i).kill();
                        bulletList.get(j).kill();
                    }
                }
            }
        }
    }

    public void render(Graphics2D g) {
        for (Bullet b : bulletList) {
            b.render(g);
        }
    }

    public void bulletsUpdate() {
        if (bulletList.isEmpty())return;

        for (Bullet b : bulletList){
            //Разобраться с lvl проверкой(|| (lvl.cheakShoot(this)))
            if ((b.getX2() >= Game.WIDTH) || (b.getX() <= 0) || (b.getY2() >= Game.HEIGHT) || (b.getY() <= 0)) {b.kill(); return;}

            if (b.getLive()) {
                switch (b.compass) {
                    case 0:
                        b.y -= b.speed;
                        break;
                    case 1:
                        b.x += b.speed;
                        break;
                    case 2:
                        b.y += b.speed;
                        break;
                    case 3:
                        b.x -= b.speed;
                        break;
                    default: //
                }
            }
            if (lvl.cheakShoot(b)) b.kill();
        }

    }

    public void shoot(Player A, int bulletMod) {
        Bullet b;
        BufferedImage image;
        switch (A.getWay()){
            case 0:
                image = viewer.getImage(0);
                b = new Bullet(A.getX()+A.getWidth()/2 -image.getWidth()/2, A.getY()-viewer.getImage(0).getHeight(), bulletMod, A.getWay(), image, lvl);
                break;
            case 1:
                image = viewer.getImage(1);
                b = new Bullet(A.getX2(), A.getY()+A.getHeight()/2-image.getHeight()/2, bulletMod,A.getWay(), image, lvl);
                break;
            case 2:
                image = viewer.getImage(2);
                b = new Bullet(A.getX()+A.getWidth()/2 -image.getWidth()/2, A.getY2(), bulletMod,A.getWay(), image, lvl);
                break;
            case 3:
                image = viewer.getImage(3);
                b = new Bullet(A.getX()-image.getWidth(), A.getY()+A.getHeight()/2-image.getHeight()/2, bulletMod,A.getWay(), image, lvl);
                break;
            default: image = viewer.getImage(0);
                    b = new Bullet(A.getX2(), A.getY(), bulletMod,A.getWay(), image, lvl);
        }

        if (Setting.BULLET_HITBOX_RENDER) b.setHitBox(true);

        bulletList.add(b);
    }

    public void tankStrikeCollision(){
        if (bulletList.isEmpty()) {
            return;
        }

        strikeA();
        strikeB();
    }

    public void strikeA(){
        for (Bullet b : bulletList){
            if (A.Collision(b)){
                A.life = false;
                b.kill();
            }
        }
    }

    public void strikeB(){
        for (Bullet b : bulletList){
            if (B.Collision(b)){
                B.life = false;
                b.kill();
            }
        }
    }

}
