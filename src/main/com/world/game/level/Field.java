package main.com.world.game.level;

import main.com.world.game.Players.GameObjects;

import java.awt.image.BufferedImage;

public class Field extends GameObjects {

    private int HP;
    private Boolean live;
    private int period;
    private float PERIOD_M;

    public Field(float x, float y, BufferedImage image, int healPoints){
        super(x,y,image);

        HP = healPoints;
        live = true;

        PERIOD_M = HP/4;
    }

    public  void damage(int a){
        HP-=a;
        Cheak();
    }

    public void Cheak(){
        if (!live) return;

        if (HP<=0){
            live=false;
        }

        period = (int)Math.floor(HP/PERIOD_M);
    }

    public Boolean getLive(){
        return live;
    }

    public int getPeriod(){
        return period;
    }
}
