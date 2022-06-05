package main.com.world.game.Players.bullet;

import main.com.world.graphics.TextureAtlas;
import main.com.world.utils.Utils;

import java.awt.image.BufferedImage;

public class BulletViewer {
    private final int SIZE = 84;
    private TextureAtlas atlas;
    private BufferedImage image;

    public BulletViewer(TextureAtlas atlas){
        this.atlas = atlas;
    }

    public BufferedImage getImage(int a){
        switch (a){
        case 0:
            image = Utils.resize(atlas.cut(4*SIZE+32,2*SIZE+13,SIZE-62,SIZE-21),10,15);
            break;
        case 1:
            image = Utils.resize(atlas.cut("resource_right.png",1*SIZE+8,4*SIZE+32,SIZE-21,SIZE-62),15,10);
            break;
        case 2:
            image = Utils.resize(atlas.cut("resource_down.png",3*SIZE+30,1*SIZE+8,SIZE-62,SIZE-21),10,15);
            break;
        case 3:
            image = Utils.resize(atlas.cut("resource_left.png",2*SIZE+13,3*SIZE+30,SIZE-21,SIZE-62),15,10);
            break;
        default: image = null;
    }
        return image;
    }
}
