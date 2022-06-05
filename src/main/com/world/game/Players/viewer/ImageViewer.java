package main.com.world.game.Players.viewer;

import main.com.world.graphics.TextureAtlas;
import main.com.world.utils.Utils;

import java.awt.image.BufferedImage;

public class ImageViewer {
    public final int SIZE = 84;

    public BufferedImage[][] Tank;
    public float speed = 0.1f;
    public Boolean TankAnim;
    public float num = 0;
    public float scale;

    public BufferedImage[] explosion;
    public float BoomCounter = 0;
    public Boolean FinishBoom;
    public Boolean SatrtBoom;

    public ImageViewer(TextureAtlas atlas, float scale){
        this.scale = scale;
        explosion = new BufferedImage[4];
        Tank = new BufferedImage[4][8];

        setExplosion(atlas);
        setLastExplosionImage();

        CleanExplosion();
        TankAnim = true;
    }

    public void setLastImage() {
        for (int i = 0; i < Tank.length; i++) {
            for (int j = 0; j < Tank[0].length; j++) {
                Tank[i][j] = Utils.resize(Tank[i][j], (int) (Tank[i][j].getWidth() * scale), (int) (Tank[i][j].getHeight() * scale));
            }
        }
    }

    public void setLastExplosionImage() {
        for (int j = 0; j < explosion.length; j++) {
            explosion[j] = Utils.resize(explosion[j], (int) (explosion[j].getWidth() * scale), (int) (explosion[j].getHeight() * scale));
        }
    }

    public void setExplosion(TextureAtlas atlas) {
        explosion[0] = atlas.cut((int) (5 * SIZE), (int) (2 * SIZE), (int) SIZE, (int) SIZE);
        explosion[1] = atlas.cut((int) (1 * SIZE), (int) (2 * SIZE), (int) SIZE, (int) SIZE);
        explosion[2] = atlas.cut((int) (2 * SIZE), (int) (2 * SIZE), (int) SIZE, (int) SIZE);
        explosion[3] = atlas.cut((int) (3 * SIZE), (int) (2 * SIZE), (int) SIZE, (int) SIZE);
    }

    public void update() {

        if (TankAnim) {
            num += speed;
            if (num >= 8) num = 0;
        }

        if (SatrtBoom) {
            BoomCounter += speed;
            if (BoomCounter >= 4) {
                BoomCounter = 0;
                FinishBoom = true;
            }
        }
    }

    public BufferedImage getNowImage(int n) {
        update();
        switch (n) {
            case 0:
                return Tank[0][(int) (num)];
            case 1:
                return Tank[1][(int) (num)];
            case 2:
                return Tank[2][(int) (num)];
            case 3:
                return Tank[3][(int) (num)];
            default:
                return Tank[0][0];
        }
    }

    public BufferedImage getExplosionImage() {
        return explosion[(int) Math.floor(BoomCounter) % 4];
    }

    public void StartExplosion() {
        TankAnim = false;
        SatrtBoom = true;
        FinishBoom = false;
    }

    public void CleanExplosion() {
        TankAnim = true;
        SatrtBoom = false;
        FinishBoom = false;
    }

    public Boolean BoomFinish() {
        return FinishBoom;
    }

    public Boolean BoomStart() {
        return SatrtBoom;
    }
}
