package main.com.world.game.level;

import main.com.world.game.Players.Update;
import main.com.world.game.Players.viewer.Texture;

public class Floor extends SolidField implements Update {
    public Floor(int x, int y) {
        super(x, y, Texture.Floor);
    }

    @Override
    public void update() {

    }
}
