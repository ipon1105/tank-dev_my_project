package main.com.world.game.Players;

import java.awt.*;

public class HitBox {

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
