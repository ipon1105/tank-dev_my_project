package main.com.world.game.Players.bullet;

import main.com.world.game.Players.GameObjects;
import main.com.world.game.level.FieldMap;
import main.com.world.setting.Setting;

import java.awt.image.BufferedImage;

public class Bullet extends GameObjects {
    public float speed;
    private float damage;
    private float cooldown;
    public int compass;
    private Boolean live;
    private FieldMap lvl;

    public Bullet(float x, float y, int typeB,int compass, BufferedImage image, FieldMap lvl) {
        super(x,y,image);
        this.lvl = lvl;
        this.compass = compass;
        live = true;

        switch (typeB) {
            case 1:
                speed = Setting.A_BULLET_SPEED;
                damage = Setting.A_BULLET_DAMAGE;
                cooldown = Setting.A_BULLET_COOLDOWN;
                break;
            case 2:
                speed = Setting.B_BULLET_SPEED;
                damage = Setting.B_BULLET_DAMAGE;
                cooldown = Setting.B_BULLET_COOLDOWN;
                break;
            case 3:
                speed = Setting.C_BULLET_SPEED;
                damage = Setting.C_BULLET_DAMAGE;
                cooldown = Setting.C_BULLET_COOLDOWN;
                break;
            default:
                speed = Setting.DEFAULT_BULLET_SPEED;
                damage = Setting.DEFAULT_BULLET_DAMAGE;
                cooldown = Setting.DEFAULT_BULLET_COOLDOWN;
                break;
        }

        this.image = image;
    }

    public Boolean getLive() {
        return live;
    }

    public Boolean Collision(GameObjects A) {
        return (((A.getX() <= getX2()) && (getX2() <= A.getX2()) || (A.getX() <= getX()) && (getX() <= A.getX2())) && ((A.getY() <= getY2()) && (getY2() <= A.getY2()) || (A.getY() <= getY()) && (getY() <= A.getY2())));
    }

    public void kill() {
        live = false;
    }

    public int getDamage(){
        return (int)damage;
    }
}
