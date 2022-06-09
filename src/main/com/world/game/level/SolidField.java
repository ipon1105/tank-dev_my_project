package main.com.world.game.level;

import main.com.world.game.Players.Update;
import main.com.world.game.Players.viewer.Texture;

public abstract class SolidField extends Field implements Update {
    protected Boolean live;
    protected int hp;

    public SolidField(int x, int y, Texture texture){
        super(x, y, texture);
    }

    public Boolean getLive() {
        return live;
    }

    public void setLive(Boolean live) {
        this.live = live;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }
}
