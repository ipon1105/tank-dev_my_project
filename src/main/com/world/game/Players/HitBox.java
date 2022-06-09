package main.com.world.game.Players;

import main.com.world.game.HitBoxDraw;

import java.awt.*;



public abstract class HitBox implements HitBoxDraw {

    public Boolean hitBox;

    public HitBox(){
        hitBox = false;
    }

    public void setHitBox(Boolean hitBox){
        this.hitBox = hitBox;
    }

    public Boolean getHitBox(){
        return this.hitBox;
    }





}

