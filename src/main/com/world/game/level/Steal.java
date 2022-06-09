package main.com.world.game.level;

import main.com.world.game.Players.Update;
import main.com.world.game.Players.viewer.Texture;
import main.com.world.setting.Setting;

public class Steal extends SolidField{

    public Steal(int x, int y) {
        super(x, y, Texture.SteelWall);

        live = true;
    }

    @Override
    public void update() {

    }
}
