package main.com.world.game.level;

import main.com.world.display.Display;
import main.com.world.game.Players.Update;
import main.com.world.game.Players.viewer.Texture;
import main.com.world.setting.Setting;

public class Brick extends SolidField implements Update {
    private int period;
    private final int PERIOD_M = Setting.WALL_2_HP / 4;

    public Brick(int x, int y) {
        super(x, y, Texture.BrickWall);

        live = true;
        hp = Setting.WALL_2_HP;
    }
    public int getPeriod(){
        return period;
    }



    public void damage(int a){
        hp-=a;
        Cheak();
    }

    public void Cheak(){
        if (!live)
            return;

        if (hp <= 0)
            live = false;

        period = (int) Math.floor(hp / PERIOD_M);
    }

    public void setImage(Texture texture){
        this.image = Display.textureBuffer.get(texture);
    }


    @Override
    public void update() {
        if (live)
            Cheak();
    }
}
